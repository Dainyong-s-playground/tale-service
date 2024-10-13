package kkk.dainyong.tale.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PurchaseBooksDTO {
    private Long id;
    private String title;
    private String imageUrl;
    private int purchasePrice;
    private String author;
    private String purchaseDate;
}
