package kkk.dainyong.tale.model.dto;

import kkk.dainyong.tale.model.FairyTale;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author dae
 * @date 10/10 03:15
 */

@Getter
@ToString
@Builder
public class FairyTaleDTO {
	private Long id;
	private String title;
	private String imageUrl;
	private int views;
	private int rentalPrice;
	private int purchasePrice;
	private String description;
	private String author;

	public static FairyTaleDTO from(FairyTale fairyTale) {
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