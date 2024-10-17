// HistoryController.java
package kkk.dainyong.tale.controller;

import java.util.List;

import kkk.dainyong.tale.model.History;
import kkk.dainyong.tale.model.dto.PastDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kkk.dainyong.tale.model.dto.HistoryDTO;
import kkk.dainyong.tale.service.HistoryService;

@RestController
@RequestMapping("/api/history")
public class HistoryController {
	private final HistoryService historyService;

	@Autowired
	public HistoryController(HistoryService historyService) {
		this.historyService = historyService;
	}

	@GetMapping("/recently-watched/{profileId}")
	public ResponseEntity<List<HistoryDTO>> getRecentlyWatchedContent(@PathVariable Long profileId) {
		List<HistoryDTO> recentlyWatched = historyService.getRecentlyWatchedContent(profileId);
		return ResponseEntity.ok(recentlyWatched);
	}

	@GetMapping("/pastData/{profileId}")
	public ResponseEntity<List<PastDataDTO>> getPastDataList(@PathVariable Long profileId) {
		// 서비스 계층에서 병합된 데이터를 가져옴
		List<PastDataDTO> result = historyService.getPastData(profileId);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/")
	public ResponseEntity insertHistory(@RequestBody History history) {
		historyService.insertHistory(history);
		return ResponseEntity.ok(HttpStatus.CREATED);
	}

	@GetMapping("/{profileId}/{fairyTaleId}/progress")
	public Float getProgress(@PathVariable("profileId") Long profileId, @PathVariable("fairyTaleId") Long fairyTaleId) {
		return historyService.getProgress(profileId, fairyTaleId);
	}
}