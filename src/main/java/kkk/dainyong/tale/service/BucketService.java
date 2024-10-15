package kkk.dainyong.tale.service;

import kkk.dainyong.tale.model.Bucket;
import kkk.dainyong.tale.model.dto.BucketDTO;
import kkk.dainyong.tale.repository.BucketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class BucketService {

    private final BucketRepository bucketRepository;

    public void addBucket(Bucket bucket){
        //먼저 있는지 체크 필요
        String loginId = bucket.getLoginId();
        Long fairyTaleId= bucket.getFairyTaleId();
        log.info(loginId);
        log.info(fairyTaleId);
        BucketDTO bucketDTO = bucketRepository.selectOneBucket(loginId, fairyTaleId);
        if(bucketDTO != null){
            return;
        }else{
            bucketRepository.insertBucket(bucket);
        }
    }

    public void deleteBucket(String loginId, Long fairyTaleId){
        bucketRepository.deleteBucket(loginId, fairyTaleId);
    }

    @Transactional(readOnly = true)
    public List<BucketDTO> selectAllBucket(String loginId){
        List<BucketDTO> bucketDTOList = bucketRepository.selectAllByLoginId(loginId);
        return bucketDTOList;
    }

    @Transactional(readOnly = true)
    public BucketDTO selectOneBucket(String loginId, Long fairyTaleId){
        BucketDTO bucketDTO = bucketRepository.selectOneBucket(loginId, fairyTaleId);
        return bucketDTO;
    }
}
