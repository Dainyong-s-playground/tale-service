package kkk.dainyong.tale.model.dto;

import kkk.dainyong.tale.model.FairyTale;
import kkk.dainyong.tale.model.History;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@Builder
public class HistoryDTO {
	private Long profileId;
	private Long fairyTaleId;
	private LocalDate readDate;
	private Float progress;
	private FairyTaleDTO fairyTale;

	public static HistoryDTO from(History history, FairyTale fairyTale) {
		return HistoryDTO.builder()
			.profileId(history.getProfileId())
			.fairyTaleId(history.getFairyTaleId())
			.readDate(history.getReadDate())
			.progress(history.getProgress())
			.fairyTale(FairyTaleDTO.from(fairyTale))
			.build();
	}
}