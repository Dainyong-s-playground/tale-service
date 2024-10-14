package kkk.dainyong.tale.controller;

import kkk.dainyong.tale.model.Bucket;
import kkk.dainyong.tale.model.PurchaseList;
import kkk.dainyong.tale.model.RentalList;
import kkk.dainyong.tale.model.Reservation;
import kkk.dainyong.tale.model.dto.BucketDTO;
import kkk.dainyong.tale.model.dto.ProfileStatsDTO;
import kkk.dainyong.tale.service.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("/deletebucket")
    public ResponseEntity<String> deleteBucket(@RequestBody Bucket bucket){
        bucketService.deleteBucket(bucket.getLoginId(), bucket.getFairyTaleId());
        return ResponseEntity.ok("");
    }

    @PostMapping("/addbucket")
    public ResponseEntity<String> addBucketList(@RequestBody Bucket bucket){
        bucketService.addBucket(bucket.getLoginId(), bucket.getFairyTaleId());
        return ResponseEntity.ok("");
    }

    @GetMapping("/checkbucket")
    public BucketDTO getAllBucket(@RequestBody Bucket bucket){
        BucketDTO bucketDTO = bucketService.selectOneBucket(bucket.getLoginId(), bucket.getFairyTaleId());
        return bucketDTO;
    }
}
