package kkk.dainyong.tale.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CommentsDTO {
    private Long id;
    private String dailyComment;
    private Boolean useComplimentBadge;
    private String readsDay;
    private Long profileId;
}
