package kkk.dainyong.tale.controller;

import kkk.dainyong.tale.model.PurchaseList;
import kkk.dainyong.tale.model.RentalList;
import kkk.dainyong.tale.model.dto.BucketDTO;
import kkk.dainyong.tale.model.dto.ProfileStatsDTO;
import kkk.dainyong.tale.service.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BucketController {
    private final BucketService bucketService;

    @GetMapping("/bucket/{loginId}")
    public List<BucketDTO> getAllBucket(@PathVariable("loginId") String loginId){
        List<BucketDTO> bucketDTOList = bucketService.selectAllBucket(loginId);
        return bucketDTOList;
    }
    @GetMapping("/bucket/{loginId}")
    public List<BucketDTO> getAllBucket(@PathVariable("loginId") String loginId){
        List<BucketDTO> bucketDTOList = bucketService.selectAllBucket(loginId);
        return bucketDTOList;
    }

}
