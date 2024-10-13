package kkk.dainyong.tale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import kkk.dainyong.tale.model.dto.FairyTaleDTO;
import kkk.dainyong.tale.service.FairyTaleService;

@RestController
@RequestMapping("/api/fairytales")
public class FairyTaleController {

	private final FairyTaleService fairyTaleService;

	@Autowired
	public FairyTaleController(FairyTaleService fairyTaleService) {
		this.fairyTaleService = fairyTaleService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<FairyTaleDTO> getFairyTale(@PathVariable Long id, HttpServletRequest request) {
		String ipAddress = request.getRemoteAddr();
		FairyTaleDTO fairyTale = fairyTaleService.getFairyTaleByIdAndIncrementViews(id, ipAddress);
		if (fairyTale != null) {
			return ResponseEntity.ok(fairyTale);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/top5")
	public ResponseEntity<List<FairyTaleDTO>> getTop5FairyTales() {
		List<FairyTaleDTO> top5FairyTales = fairyTaleService.getTop5FairyTalesByViews();
		return ResponseEntity.ok(top5FairyTales);
	}

	@GetMapping("/{id}/recommendations")
	public ResponseEntity<List<FairyTaleDTO>> getRecommendations(@PathVariable Long id) {
		List<FairyTaleDTO> recommendations = fairyTaleService.getRandomThreeRecommendations(id);
		return ResponseEntity.ok(recommendations);
	}

}
