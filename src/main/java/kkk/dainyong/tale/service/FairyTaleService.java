package kkk.dainyong.tale.service;

import kkk.dainyong.tale.model.dto.FairyTaleDTO;
import kkk.dainyong.tale.repository.FairyTaleRepository;
import kkk.dainyong.tale.model.FairyTale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
}