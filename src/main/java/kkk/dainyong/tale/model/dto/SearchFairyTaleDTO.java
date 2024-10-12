package kkk.dainyong.tale.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class SearchFairyTaleDTO {
    private Long id;
    private String fairyTaleTitle;
    private String fairyTaleImage;
    private int views;
    private int rentalPrice;
    private int purchasePrice;
    private String description;
    private String author;
    private List<String> tag;
}
