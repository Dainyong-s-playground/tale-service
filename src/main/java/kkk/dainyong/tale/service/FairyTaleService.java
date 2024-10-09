package kkk.dainyong.tale.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kkk.dainyong.tale.model.FairyTale;
import kkk.dainyong.tale.model.dto.FairyTaleDTO;
import kkk.dainyong.tale.repository.FairyTaleRepository;

@Service
public class FairyTaleService {

	private final FairyTaleRepository fairyTaleRepository;

	@Autowired
	public FairyTaleService(FairyTaleRepository fairyTaleRepository) {
		this.fairyTaleRepository = fairyTaleRepository;
	}

	@Transactional(readOnly = true)
	public List<FairyTaleDTO> getTop5FairyTalesByViews() {
		List<FairyTale> top5FairyTales = fairyTaleRepository.findTop5ByOrderByViewsDesc();
		return top5FairyTales.stream()
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

		// 조회수를 항상 증가시킵니다.
		fairyTaleRepository.incrementViews(fairyTaleId);

		// 업데이트된 정보를 다시 가져옵니다.
		fairyTale = fairyTaleRepository.findById(fairyTaleId);

		return convertToDTO(fairyTale);
	}
}