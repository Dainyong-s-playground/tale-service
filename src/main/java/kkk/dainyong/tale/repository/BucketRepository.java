package kkk.dainyong.tale.repository;

import kkk.dainyong.tale.model.Bucket;
import kkk.dainyong.tale.model.dto.BucketDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BucketRepository {
    void insertBucket(Bucket bucket);
    void deleteBucket(String loginId, Long fairyTaleId);
    List<BucketDTO> selectAllByLoginId(@Param("loginId") String loginId);
    BucketDTO selectOneBucket(@Param("loginId") String loginId, @Param("fairyTaleId") Long fairyTaleId);
}
