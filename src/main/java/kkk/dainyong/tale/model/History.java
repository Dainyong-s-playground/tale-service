package kkk.dainyong.tale.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class History {
	private Long profileId;
	private Long fairyTaleId;
	private LocalDate readDate;
	private Float progress;
}