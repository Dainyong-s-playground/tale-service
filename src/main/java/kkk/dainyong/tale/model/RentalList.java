package kkk.dainyong.tale.model;

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
public class RentalList {
	private String userId;
	private Long fairyTaleId;  // fairytaleId를 fairyTaleId로 변경
	private Date startDate;
	private Date endDate;
}