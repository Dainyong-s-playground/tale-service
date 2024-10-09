package kkk.dainyong.tale.model.dto;

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
}