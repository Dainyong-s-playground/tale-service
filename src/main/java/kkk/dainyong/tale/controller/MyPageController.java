package kkk.dainyong.tale.controller;

import kkk.dainyong.tale.model.FairyTale;
import kkk.dainyong.tale.model.PurchaseList;
import kkk.dainyong.tale.model.RentalList;
import kkk.dainyong.tale.model.dto.FairyTaleDTO;
import kkk.dainyong.tale.model.dto.ProfileStatsDTO;
import kkk.dainyong.tale.model.dto.PurchaseBooksDTO;
import kkk.dainyong.tale.model.dto.RentalBooksDTO;
import kkk.dainyong.tale.repository.FairyTaleOwnershipRepository;
import kkk.dainyong.tale.service.FairyTaleOwnershipService;
import kkk.dainyong.tale.service.FairyTaleService;
import kkk.dainyong.tale.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mypage")
public class MyPageController {


    private final MyPageService myPageService;

    @GetMapping("/buyingStats/{loginId}")
    public ProfileStatsDTO getBuyingStats(@PathVariable("loginId") String loginId){
        List<RentalList> rentalLists = myPageService.getRentalListByUserId(loginId);
        List<PurchaseList> purchaseLists =myPageService.getPurchaseListByUserId(loginId);

        ProfileStatsDTO profileStatsDTO = ProfileStatsDTO.builder()
                .totalPurchaseCount(purchaseLists.size())
                .totalRentalCount(rentalLists.size())
                .build();
        log.info(profileStatsDTO);
        return profileStatsDTO;

    }

    @GetMapping("/rental/{loginId}")
    public List<RentalBooksDTO> getRentalList(@PathVariable("loginId") String loginId){
        // rentalBooksList 초기화
        List<RentalBooksDTO> rentalBooksList = new ArrayList<>();

        // rentalLists 가져오기
        List<RentalList> rentalLists = myPageService.getRentalListByUserId(loginId);

        // for-each 루프 사용하여 가독성 향상
        for (RentalList rental : rentalLists) {
            // FairyTale 객체 조회
            FairyTale fairyTale = myPageService.findById(rental.getFairyTaleId());
            // RentalBooksDTO 생성 및 추가
            RentalBooksDTO rentalBooksDTO = RentalBooksDTO.builder()
                    .id(fairyTale.getId())
                    .title(fairyTale.getTitle())
                    .imageUrl(fairyTale.getImageUrl())
                    .author(fairyTale.getAuthor())
                    .rentalPrice(fairyTale.getRentalPrice())
                    .startDate(rental.getStartDate().toString())
                    .endDate(rental.getEndDate().toString())
                    .build();

            rentalBooksList.add(rentalBooksDTO);
        }

        return rentalBooksList;
    }

    @GetMapping("/purchase/{loginId}")
    public List<PurchaseBooksDTO> getPurchaseList(@PathVariable("loginId") String loginId){
        List<PurchaseBooksDTO> purchaseBooksList = new ArrayList<>();

        List<PurchaseList> purchaseLists =myPageService.getPurchaseListByUserId(loginId);

        for (PurchaseList purchaseList : purchaseLists) {
            // FairyTale 객체 조회
            FairyTale fairyTale = myPageService.findById(purchaseList.getFairyTaleId());
            // RentalBooksDTO 생성 및 추가
            PurchaseBooksDTO purchaseBooksDTO= PurchaseBooksDTO.builder()
                    .id(fairyTale.getId())
                    .title(fairyTale.getTitle())
                    .imageUrl(fairyTale.getImageUrl())
                    .author(fairyTale.getAuthor())
                    .purchasePrice(fairyTale.getPurchasePrice())
                    .purchaseDate(purchaseList.getPurchaseDate().toString())
                    .build();

            purchaseBooksList.add(purchaseBooksDTO);
        }

        return purchaseBooksList;

    }
}
