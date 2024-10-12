package kkk.dainyong.tale.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDTO {
    private Long reservationId;
    private Long fairyTaleId;
    private String fairyTaleTitle;
    private String fairyTaleImage;
    private String reservationDate;
}
