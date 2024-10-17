package kkk.dainyong.tale.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketDTO {
    private Long id;
    private String title;
    private String image;
    private String rentalPrice;
    private String purchasePrice;
}
