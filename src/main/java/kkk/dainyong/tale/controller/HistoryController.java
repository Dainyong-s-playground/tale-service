// HistoryController.java
package kkk.dainyong.tale.controller;

import java.util.List;

import kkk.dainyong.tale.model.dto.PastDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	// 필요한 경우 다른 엔드포인트 추가
}