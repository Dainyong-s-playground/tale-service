package kkk.dainyong.tale.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FairyTaleDataDTO {
    private Long id;
    private String title;
    private String image;           // 표지
    private String speaker;
    private String script;
    private List<String> url;       // 이미지 리소스
    private String sceneNumber;     // 화면 전환 장면
}
