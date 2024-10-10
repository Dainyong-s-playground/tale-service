package kkk.dainyong.tale.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FairyTaleOwnershipDTO {
	// 동화 기본 정보
	private Long fairyTaleId;
	private String title;
	private String imageUrl;
	private String description;
	private int views;

	// 소유권 관련 정보
	private boolean isPurchased;
	private boolean isRented;
	private Date acquisitionDate;
	private Date expirationDate;

	// 가격 정보
	private int rentalPrice;
	private int purchasePrice;

	// 진행률
	private Double progress;
}