package kkk.dainyong.tale.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalList {
	private String userId;
	private Long fairyTaleId;
	private Date startDate;
	private Date endDate;
}