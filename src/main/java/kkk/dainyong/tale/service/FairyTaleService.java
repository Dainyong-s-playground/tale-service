package kkk.dainyong.tale.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import kkk.dainyong.tale.model.dto.UpdatePreferenceDTO;
import kkk.dainyong.tale.repository.PreferenceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kkk.dainyong.tale.model.FairyTale;
import kkk.dainyong.tale.model.dto.FairyTaleDTO;
import kkk.dainyong.tale.repository.FairyTaleRepository;

@Service
@Slf4j
public class FairyTaleService {

	private final FairyTaleRepository fairyTaleRepository;
	private final PreferenceRepository preferenceRepository;

	@Autowired
	public FairyTaleService(FairyTaleRepository fairyTaleRepository, PreferenceRepository preferenceRepository) {
		this.fairyTaleRepository = fairyTaleRepository;
        this.preferenceRepository = preferenceRepository;
    }

	@Transactional
	public FairyTaleDTO getFairyTaleByIdAndIncrementViews(Long id, String ipAddress) {
		FairyTale fairyTale = fairyTaleRepository.findById(id);
		if (fairyTale == null) {
			return null;
		}

		// 조회수 증가 로직을 여기서만 실행
		incrementViewsIfAllowed(id, ipAddress);

		// 업데이트된 정보를 다시 가져옵니다.
		fairyTale = fairyTaleRepository.findById(id);

		return convertToDTO(fairyTale);
	}

	@Transactional(readOnly = true)
	public List<FairyTaleDTO> getTop5FairyTalesByViews() {
		List<FairyTale> top5FairyTales = fairyTaleRepository.findTop5ByOrderByViewsDesc();
		return top5FairyTales.stream()
			.map(this::convertToDTO)
			.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<FairyTaleDTO> getRandomThreeRecommendations(Long excludeId) {
		List<FairyTale> recommendations = fairyTaleRepository.findRandomThreeFromTopTenExcludingId(excludeId);
		return recommendations.stream()
			.map(this::convertToDTO)
			.collect(Collectors.toList());
	}

	private FairyTaleDTO convertToDTO(FairyTale fairyTale) {
		return FairyTaleDTO.builder()
			.id(fairyTale.getId())
			.title(fairyTale.getTitle())
			.imageUrl(fairyTale.getImageUrl())
			.views(fairyTale.getViews())
			.rentalPrice(fairyTale.getRentalPrice())
			.purchasePrice(fairyTale.getPurchasePrice())
			.description(fairyTale.getDescription())
			.author(fairyTale.getAuthor())
			.build();
	}

	@Transactional
	public FairyTaleDTO incrementViews(Long fairyTaleId, String ipAddress) {
		FairyTale fairyTale = fairyTaleRepository.findById(fairyTaleId);
		if (fairyTale == null) {
			throw new RuntimeException("동화를 찾을 수 없습니다.");
		}

		incrementViewsIfAllowed(fairyTaleId, ipAddress);

		// 업데이트된 정보를 다시 가져옵니다.
		fairyTale = fairyTaleRepository.findById(fairyTaleId);

		return convertToDTO(fairyTale);
	}

	private void incrementViewsIfAllowed(Long fairyTaleId, String ipAddress) {
		LocalDate viewDate = LocalDate.now();
		if (fairyTaleRepository.canIncrementViews(fairyTaleId, ipAddress, viewDate) > 0) {
			fairyTaleRepository.incrementViews(fairyTaleId);
			fairyTaleRepository.insertViewLog(fairyTaleId, ipAddress, viewDate);
		}
	}

	public void updatePreferencesCount(UpdatePreferenceDTO updatePreferenceDTO) {
		log.info("Updating preferences for profileId: {} and fairyTaleId: {}",
				updatePreferenceDTO.getProfileId(), updatePreferenceDTO.getFairyTaleId());

		int updatedRows = preferenceRepository.updatePreferencesCount(
				updatePreferenceDTO.getProfileId(),
				updatePreferenceDTO.getFairyTaleId()
		);

		log.info("Preferences updated successfully. Affected rows: {}", updatedRows);
	}
}
