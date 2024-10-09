package kkk.dainyong.tale.controller;

import kkk.dainyong.tale.model.dto.FairyTaleDTO;
import kkk.dainyong.tale.service.FairyTaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/fairytales")
public class FairyTaleController {

	private final FairyTaleService fairyTaleService;

	@Autowired
	public FairyTaleController(FairyTaleService fairyTaleService) {
		this.fairyTaleService = fairyTaleService;
	}

	@GetMapping("/top5")
	public ResponseEntity<List<FairyTaleDTO>> getTop5FairyTales() {
		List<FairyTaleDTO> top5FairyTales = fairyTaleService.getTop5FairyTalesByViews();
		return ResponseEntity.ok(top5FairyTales);
	}
}