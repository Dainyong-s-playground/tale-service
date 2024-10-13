package kkk.dainyong.tale.service;

import kkk.dainyong.tale.model.FairyTale;
import kkk.dainyong.tale.model.PurchaseList;
import kkk.dainyong.tale.model.RentalList;
import kkk.dainyong.tale.repository.FairyTaleOwnershipRepository;
import kkk.dainyong.tale.repository.FairyTaleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPageService {

    private final FairyTaleRepository fairyTaleRepository;
    private final FairyTaleOwnershipRepository fairyTaleOwnershipRepository;

    @Transactional(readOnly = true)
    public FairyTale findById( Long id){
        FairyTale fairyTale = fairyTaleRepository.findById(id);
        return fairyTale;
    }

    public List<RentalList> getRentalListByUserId(String userId){
        List<RentalList> rentalLists = fairyTaleOwnershipRepository.findRentalListByUserId(userId);

        return rentalLists;
    }

    public List<PurchaseList> getPurchaseListByUserId(String userId) {
        List<PurchaseList> purchaseLists = fairyTaleOwnershipRepository.findPurchaseListByUserId(userId);
        return purchaseLists;
    }
}
