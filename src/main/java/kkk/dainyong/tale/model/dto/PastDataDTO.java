package kkk.dainyong.tale.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PastDataDTO {
    private String fairyTaleTitle;
    private String fairyTaleImage;
    private String readsDay; // 날짜 필드는 String으로 매핑
}
