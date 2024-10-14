package kkk.dainyong.tale.service;

import kkk.dainyong.tale.model.dto.BucketDTO;
import kkk.dainyong.tale.repository.BucketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BucketService {

    private final BucketRepository bucketRepository;

    public void addBucket(String loginId, Long fairyTaleId){
        //먼저 있는지 체크 필요
        BucketDTO bucketDTO = bucketRepository.selectOneBucket(loginId, fairyTaleId);
        if(bucketDTO != null){
            return;
        }else{
            bucketRepository.insertBucket(loginId,fairyTaleId);
        }
    }

    public void deleteBucket(String loginId, Long fairyTaleId){
        bucketRepository.deleteBucket(loginId,fairyTaleId);
    }

    public List<BucketDTO> selectAllBucket(String loginId){
        List<BucketDTO> bucketDTOList = bucketRepository.selectAllByLoginId(loginId);
        return bucketDTOList;
    }

    public BucketDTO selectOneBucket(String loginId, Long fairyTaleId){
        BucketDTO bucketDTO = bucketRepository.selectOneBucket(loginId, fairyTaleId);
        return bucketDTO;
    }
}
