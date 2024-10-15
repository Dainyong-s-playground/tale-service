package kkk.dainyong.tale.repository;

import kkk.dainyong.tale.model.Reports;
import kkk.dainyong.tale.model.dto.PreferenceDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GraphRepository {
    Reports getReports(@Param("profileId") Long profileId);
    List<PreferenceDTO> getPreferenceByProfileId(@Param("profileId") Long profileId);

    void incrementTotalCount(@Param("profileId") Long profileId);

    void incrementRecordCount(@Param("profileId") Long profileId);

    void incrementMotionCount(@Param("profileId") Long profileId);
}
