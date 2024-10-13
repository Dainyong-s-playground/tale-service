package kkk.dainyong.tale.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kkk.dainyong.tale.model.FairyTale;
import kkk.dainyong.tale.model.History;
import kkk.dainyong.tale.model.PurchaseList;
import kkk.dainyong.tale.model.RentalList;
import kkk.dainyong.tale.model.dto.FairyTaleOwnershipDTO;
import kkk.dainyong.tale.repository.FairyTaleOwnershipRepository;
import kkk.dainyong.tale.repository.HistoryRepository;

@Service
public class FairyTaleOwnershipService {

	private static final Logger logger = LoggerFactory.getLogger(FairyTaleOwnershipService.class);
	private final FairyTaleOwnershipRepository fairyTaleOwnershipRepository;
	private final HistoryRepository historyRepository;

	@Autowired
	public FairyTaleOwnershipService(FairyTaleOwnershipRepository fairyTaleOwnershipRepository,
		HistoryRepository historyRepository) {
		this.fairyTaleOwnershipRepository = fairyTaleOwnershipRepository;
		this.historyRepository = historyRepository;
	}

	@Transactional(readOnly = true)
	public FairyTaleOwnershipDTO checkOwnership(Long profileId, Long fairyTaleId) {
		logger.info("Checking ownership for profileId: {} and fairyTaleId: {}", profileId, fairyTaleId);
		String userId = getUserIdFromProfile(profileId);
		FairyTale fairyTale = getFairyTaleById(fairyTaleId);

		PurchaseList purchase = fairyTaleOwnershipRepository.findPurchaseByUserIdAndFairyTaleId(userId, fairyTaleId);
		RentalList rental = fairyTaleOwnershipRepository.findActiveRentalByUserIdAndFairyTaleId(userId, fairyTaleId);
		History history = historyRepository.getLatestHistory(profileId, fairyTaleId);

		return createFairyTaleOwnershipDTO(fairyTale, purchase, rental, history);
	}

	@Transactional
	public FairyTaleOwnershipDTO rentFairyTale(Long profileId, Long fairyTaleId) {
		logger.info("Renting fairy tale for profileId: {} and fairyTaleId: {}", profileId, fairyTaleId);
		String userId = getUserIdFromProfile(profileId);
		FairyTale fairyTale = getFairyTaleById(fairyTaleId);

		// 이미 구매한 경우 체크
		PurchaseList existingPurchase = fairyTaleOwnershipRepository.findPurchaseByUserIdAndFairyTaleId(userId,
			fairyTaleId);
		if (existingPurchase != null) {
			logger.warn("User {} already purchased fairy tale {}", userId, fairyTaleId);
			throw new IllegalStateException("이미 소장 중인 동화입니다.");
		}

		// 이미 대여 중인 경우 체크
		RentalList existingRental = fairyTaleOwnershipRepository.findActiveRentalByUserIdAndFairyTaleId(userId,
			fairyTaleId);
		if (existingRental != null) {
			logger.warn("User {} already rented fairy tale {}", userId, fairyTaleId);
			throw new IllegalStateException("이미 대여 중인 동화입니다. 대여 만료일: " + existingRental.getEndDate());
		}

		Date startDate = new Date();
		Date endDate = calculateEndDate(startDate, 7); // 7일 대여 기간

		RentalList rental = RentalList.builder()
			.userId(userId)
			.fairyTaleId(fairyTaleId)
			.startDate(startDate)
			.endDate(endDate)
			.build();

		fairyTaleOwnershipRepository.saveRental(rental);
		logger.info("Fairy tale rented successfully");

		History history = historyRepository.getLatestHistory(profileId, fairyTaleId);
		return createFairyTaleOwnershipDTO(fairyTale, null, rental, history);
	}

	@Transactional
	public FairyTaleOwnershipDTO buyFairyTale(Long profileId, Long fairyTaleId) {
		logger.info("Buying fairy tale for profileId: {} and fairyTaleId: {}", profileId, fairyTaleId);
		String userId = getUserIdFromProfile(profileId);
		FairyTale fairyTale = getFairyTaleById(fairyTaleId);

		// 이미 구매한 경우 체크
		PurchaseList existingPurchase = fairyTaleOwnershipRepository.findPurchaseByUserIdAndFairyTaleId(userId,
			fairyTaleId);
		if (existingPurchase != null) {
			logger.warn("User {} already purchased fairy tale {}", userId, fairyTaleId);
			throw new IllegalStateException("이미 소장 중인 동화입니다.");
		}

		PurchaseList purchase = PurchaseList.builder()
			.userId(userId)
			.fairyTaleId(fairyTaleId)
			.purchaseDate(new Date())
			.build();

		fairyTaleOwnershipRepository.savePurchase(purchase);
		logger.info("Fairy tale purchased successfully");

		History history = historyRepository.getLatestHistory(profileId, fairyTaleId);
		return createFairyTaleOwnershipDTO(fairyTale, purchase, null, history);
	}

	@Transactional(readOnly = true)
	public List<RentalList> getRentalListByProfileId(Long profileId) {
		logger.info("Getting rental list for profileId: {}", profileId);
		return fairyTaleOwnershipRepository.findRentalListByProfileId(profileId);
	}

	@Transactional(readOnly = true)
	public List<PurchaseList> getPurchaseListByProfileId(Long profileId) {
		logger.info("Getting purchase list for profileId: {}", profileId);
		List<PurchaseList> purchaseList = fairyTaleOwnershipRepository.findPurchaseListByProfileId(profileId);
		logger.info("Retrieved purchase list: {}", purchaseList);
		return purchaseList;
	}

	private String getUserIdFromProfile(Long profileId) {
		String userId = fairyTaleOwnershipRepository.findUserIdByProfileId(profileId);
		logger.debug("Found userId: {} for profileId: {}", userId, profileId);
		if (userId == null) {
			logger.error("User not found for profileId: {}", profileId);
			throw new IllegalArgumentException("사용자를 찾을 수 없습니다. 프로필 ID: " + profileId);
		}
		return userId;
	}

	private FairyTale getFairyTaleById(Long fairyTaleId) {
		FairyTale fairyTale = fairyTaleOwnershipRepository.findFairyTaleById(fairyTaleId);
		if (fairyTale == null) {
			logger.error("Fairy tale not found for id: {}", fairyTaleId);
			throw new IllegalArgumentException("동화를 찾을 수 없습니다. ID: " + fairyTaleId);
		}
		return fairyTale;
	}

	private Date calculateEndDate(Date startDate, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
	}

	private FairyTaleOwnershipDTO createFairyTaleOwnershipDTO(FairyTale fairyTale, PurchaseList purchase,
		RentalList rental, History history) {
		return FairyTaleOwnershipDTO.builder()
			.fairyTaleId(fairyTale.getId())
			.title(fairyTale.getTitle())
			.imageUrl(fairyTale.getImageUrl())
			.isPurchased(purchase != null)
			.isRented(rental != null)
			.acquisitionDate(
				purchase != null ? purchase.getPurchaseDate() : (rental != null ? rental.getStartDate() : null))
			.expirationDate(rental != null ? rental.getEndDate() : null)
			.rentalPrice(fairyTale.getRentalPrice())
			.purchasePrice(fairyTale.getPurchasePrice())
			.description(fairyTale.getDescription())
			.views(fairyTale.getViews())
			.progress(history != null ? history.getProgress() : 0.0)
			.build();
	}
}