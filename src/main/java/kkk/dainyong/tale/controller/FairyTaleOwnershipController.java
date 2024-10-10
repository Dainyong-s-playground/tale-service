package kkk.dainyong.tale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kkk.dainyong.tale.model.dto.FairyTaleOwnershipDTO;
import kkk.dainyong.tale.service.FairyTaleOwnershipService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/fairy-tale-ownership")
public class FairyTaleOwnershipController {

	private final FairyTaleOwnershipService fairyTaleOwnershipService;

	@Autowired
	public FairyTaleOwnershipController(FairyTaleOwnershipService fairyTaleOwnershipService) {
		this.fairyTaleOwnershipService = fairyTaleOwnershipService;
	}

	@GetMapping("/check/{profileId}/{fairyTaleId}")
	public ResponseEntity<FairyTaleOwnershipDTO> checkOwnership(
		@PathVariable Long profileId,
		@PathVariable Long fairyTaleId) {
		FairyTaleOwnershipDTO ownershipDTO = fairyTaleOwnershipService.checkOwnership(profileId, fairyTaleId);
		return ResponseEntity.ok(ownershipDTO);
	}

	@PostMapping("/rent")
	public ResponseEntity<FairyTaleOwnershipDTO> rentFairyTale(@RequestBody OwnershipRequest request) {
		FairyTaleOwnershipDTO result = fairyTaleOwnershipService.rentFairyTale(request.getProfileId(),
			request.getFairyTaleId());
		return ResponseEntity.ok(result);
	}

	@PostMapping("/purchase")
	public ResponseEntity<FairyTaleOwnershipDTO> purchaseFairyTale(@RequestBody OwnershipRequest request) {
		FairyTaleOwnershipDTO result = fairyTaleOwnershipService.buyFairyTale(request.getProfileId(),
			request.getFairyTaleId());
		return ResponseEntity.ok(result);
	}
}

@Getter
@RequiredArgsConstructor
class OwnershipRequest {
	private final Long profileId;
	private final Long fairyTaleId;
}