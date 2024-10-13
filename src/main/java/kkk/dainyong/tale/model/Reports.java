package kkk.dainyong.tale.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reports {
    private Long id;
    private Long count;
    private Long recordCount;
    private Long motionCount;
    private Long gameCount;

}
