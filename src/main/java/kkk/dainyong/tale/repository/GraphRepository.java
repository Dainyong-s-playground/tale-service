package kkk.dainyong.tale.repository;

import kkk.dainyong.tale.model.Reports;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GraphRepository {
    Reports getReports(@Param("profileId") Long profileId);
}
