package kkk.dainyong.tale.model;

import lombok.*;


@Getter
@ToString
@Builder
public class FairyTale {
	private Long id;
	private String title;
	private String imageUrl;
	private int views;
	private int rentalPrice;
	private int purchasePrice;
	private String description;
	private String author;
}