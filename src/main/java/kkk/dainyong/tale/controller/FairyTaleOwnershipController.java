package kkk.dainyong.tale.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kkk.dainyong.tale.exception.InsufficientCreditException;
import kkk.dainyong.tale.model.PurchaseList;
import kkk.dainyong.tale.model.RentalList;
import kkk.dainyong.tale.model.dto.FairyTaleOwnershipDTO;
import kkk.dainyong.tale.service.FairyTaleOwnershipService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/fairy-tale-ownership")
public class FairyTaleOwnershipController {

	private static final Logger logger = LoggerFactory.getLogger(FairyTaleOwnershipController.class);

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
	public ResponseEntity<?> rentFairyTale(@RequestBody OwnershipRequest request) {
		try {
			logger.info("Attempting to rent fairy tale for profileId: {} and fairyTaleId: {}",
				request.getProfileId(), request.getFairyTaleId());
			FairyTaleOwnershipDTO result = fairyTaleOwnershipService.rentFairyTale(request.getProfileId(),
				request.getFairyTaleId());
			logger.info("Successfully rented fairy tale for profileId: {}", request.getProfileId());
			return ResponseEntity.ok(result);
		} catch (InsufficientCreditException e) {
			logger.warn("Insufficient credit to rent fairy tale: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(new ErrorResponse(e.getMessage()));
		} catch (IllegalArgumentException e) {
			logger.warn("Invalid argument: {}", e.getMessage());
			return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
		} catch (Exception e) {
			logger.error("Unexpected error while renting fairy tale", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("서버 내부 오류가 발생했습니다."));
		}
	}

	@PostMapping("/purchase")
	public ResponseEntity<?> purchaseFairyTale(@RequestBody OwnershipRequest request) {
		try {
			logger.info("Attempting to purchase fairy tale for profileId: {} and fairyTaleId: {}",
				request.getProfileId(), request.getFairyTaleId());
			FairyTaleOwnershipDTO result = fairyTaleOwnershipService.purchaseFairyTale(request.getProfileId(),
				request.getFairyTaleId());
			logger.info("Successfully purchased fairy tale for profileId: {}", request.getProfileId());
			return ResponseEntity.ok(result);
		} catch (InsufficientCreditException e) {
			logger.warn("Insufficient credit to purchase fairy tale: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(new ErrorResponse(e.getMessage()));
		} catch (IllegalArgumentException e) {
			logger.warn("Invalid argument: {}", e.getMessage());
			return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
		} catch (Exception e) {
			logger.error("Unexpected error while purchasing fairy tale", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("서버 내부 오류가 발생했습니다."));
		}
	}

	@GetMapping("/rental-list/{profileId}")
	public ResponseEntity<List<RentalList>> getRentalList(@PathVariable Long profileId) {
		List<RentalList> rentalList = fairyTaleOwnershipService.getRentalListByProfileId(profileId);
		return ResponseEntity.ok(rentalList);
	}

	@GetMapping("/purchase-list/{profileId}")
	public ResponseEntity<List<PurchaseList>> getPurchaseList(@PathVariable Long profileId) {
		List<PurchaseList> purchaseList = fairyTaleOwnershipService.getPurchaseListByProfileId(profileId);
		return ResponseEntity.ok(purchaseList);
	}
}

@Getter
@RequiredArgsConstructor
class OwnershipRequest {
	private final Long profileId;
	private final Long fairyTaleId;
}

@Getter
@RequiredArgsConstructor
class RentRequest {
	private final Long profileId;
	private final Long fairyTaleId;
}

@Getter
@RequiredArgsConstructor
class ErrorResponse {
	private final String message;
}
