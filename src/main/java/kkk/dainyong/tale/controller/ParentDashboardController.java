package kkk.dainyong.tale.controller;

import kkk.dainyong.tale.model.History;
import kkk.dainyong.tale.model.dto.CommentsDTO;
import kkk.dainyong.tale.model.dto.PastDataDTO;
import kkk.dainyong.tale.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class ParentDashboardController {
    @Autowired
    private HistoryService historyService;

    @GetMapping("/pastData/{profileId}")
    public ResponseEntity<List<PastDataDTO>> getPastDataList(@PathVariable Long profileId) {
        // 서비스 계층에서 병합된 데이터를 가져옴
       List<PastDataDTO> result = historyService.getPastData(profileId);
       return ResponseEntity.ok(result);
    }

    @GetMapping("/comment/{profileId}")
    public ResponseEntity<List<CommentsDTO>> getPastData(@PathVariable Long profileId) {
        // 서비스 계층에서 병합된 데이터를 가져옴
        List<CommentsDTO> result = historyService.getComments(profileId);
        return ResponseEntity.ok(result);
    }
}
