package kkk.dainyong.tale.controller;

import kkk.dainyong.tale.model.PurchaseList;
import kkk.dainyong.tale.model.RentalList;
import kkk.dainyong.tale.model.dto.ProfileStatsDTO;
import kkk.dainyong.tale.repository.FairyTaleOwnershipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mypage")
public class MyPageController {

    private final FairyTaleOwnershipRepository fairyTaleOwnershipRepository;

    @GetMapping("/buyingStats/{profileId}")
    public ProfileStatsDTO getBuyingStats(@PathVariable("profileId") Long profileId){
        List<RentalList> rentalLists = fairyTaleOwnershipRepository.findRentalListByProfileId(profileId);
        List<PurchaseList> purchaseLists = fairyTaleOwnershipRepository.findPurchaseListByProfileId(profileId);

        ProfileStatsDTO profileStatsDTO = ProfileStatsDTO.builder()
                .totalPurchaseCount(purchaseLists.size())
                .totalRentalCount(rentalLists.size())
                .build();
        log.info(profileStatsDTO);
        return profileStatsDTO;

    }

}
