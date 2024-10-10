package kkk.dainyong.tale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kkk.dainyong.tale.model.dto.FairyTaleOwnershipDTO;
import kkk.dainyong.tale.service.FairyTaleOwnershipService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
		System.out.println("Received request to rent fairyTale: " + request.getFairyTaleId() + " for profileId: " + request.getProfileId());
		FairyTaleOwnershipDTO result = fairyTaleOwnershipService.rentFairyTale(request.getProfileId(), request.getFairyTaleId());
		return ResponseEntity.ok(result);
	}

	@PostMapping("/purchase")
	public ResponseEntity<FairyTaleOwnershipDTO> purchaseFairyTale(@RequestBody OwnershipRequest request) {
		System.out.println("Received request to purchase fairyTale: " + request.getFairyTaleId() + " for profileId: " + request.getProfileId());
		FairyTaleOwnershipDTO result = fairyTaleOwnershipService.buyFairyTale(request.getProfileId(), request.getFairyTaleId());
		return ResponseEntity.ok(result);
	}
}

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class OwnershipRequest {
	// getters and setters
	private Long profileId;
	private Long fairyTaleId;

}