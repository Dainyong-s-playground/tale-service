package kkk.dainyong.tale.controller;

import kkk.dainyong.tale.model.dto.FairyTaleDataDTO;
import kkk.dainyong.tale.service.FairyTalePlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fairyTales")
public class FairyTalePlayerController {

    private final FairyTalePlayerService fairyTalePlayerService;

    @GetMapping("/{fairyTaleNumber}")
    public ResponseEntity<FairyTaleDataDTO> getFairyTaleData(@PathVariable Long fairyTaleNumber) {
        return ResponseEntity.ok(fairyTalePlayerService.getFairyTaleData(fairyTaleNumber));
    }

}
