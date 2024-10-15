package kkk.dainyong.tale.controller;

import kkk.dainyong.tale.model.Bucket;
import kkk.dainyong.tale.model.PurchaseList;
import kkk.dainyong.tale.model.RentalList;
import kkk.dainyong.tale.model.Reservation;
import kkk.dainyong.tale.model.dto.BucketDTO;
import kkk.dainyong.tale.model.dto.ProfileStatsDTO;
import kkk.dainyong.tale.service.BucketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bucket")
public class BucketController {
    private final BucketService bucketService;

    @GetMapping("/{loginId}")
    public List<BucketDTO> getAllBucket(@PathVariable("loginId") String loginId){
        List<BucketDTO> bucketDTOList = bucketService.selectAllBucket(loginId);
        return bucketDTOList;
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBucket(@RequestParam String loginId,
                                               @RequestParam Long fairyTaleId){
        log.info(loginId);
        log.info(fairyTaleId);
        bucketService.deleteBucket(loginId, fairyTaleId);
        return ResponseEntity.ok("");
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBucketList(@RequestBody Bucket bucket){
        log.info(bucket.getLoginId());
        log.info(bucket.getFairyTaleId());
        bucketService.addBucket(bucket);
        return ResponseEntity.ok("");
    }

    @GetMapping("/check")
    public BucketDTO getOneBucket(@RequestParam String loginId,
                                  @RequestParam Long fairyTaleId){
        log.info(loginId);
        log.info(fairyTaleId);
        BucketDTO bucketDTO = bucketService.selectOneBucket(loginId, fairyTaleId);
        return bucketDTO;
    }
}
