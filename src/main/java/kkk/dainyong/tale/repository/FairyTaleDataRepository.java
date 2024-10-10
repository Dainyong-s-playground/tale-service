package kkk.dainyong.tale.repository;

import kkk.dainyong.tale.model.dto.FairyTaleDataDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FairyTaleDataRepository {

    FairyTaleDataDTO getFairyTaleData(@Param("fairytaleId") Long fairytaleId);
}
