package kkk.dainyong.tale.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FairyTaleTtsDTO {

    @NotNull
    private String sentence;
    private String language;
}
