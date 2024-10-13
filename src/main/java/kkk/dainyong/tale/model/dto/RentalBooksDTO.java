package kkk.dainyong.tale.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class RentalBooksDTO {
    private Long id;
    private String title;
    private String imageUrl;
    private int rentalPrice;
    private String author;
    private String startDate;
    private String endDate;
}
