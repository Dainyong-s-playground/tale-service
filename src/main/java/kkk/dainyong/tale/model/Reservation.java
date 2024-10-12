package kkk.dainyong.tale.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    private Long profileId;
    private Long fairyTaleId;
    private String reservationDate;
}
