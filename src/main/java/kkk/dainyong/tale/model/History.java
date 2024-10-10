package kkk.dainyong.tale.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class History {
	private Long profileId;
	private Long fairyTaleId;
	private LocalDate readDate;
	private Float progress;
}