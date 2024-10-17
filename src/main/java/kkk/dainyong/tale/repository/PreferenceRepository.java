package kkk.dainyong.tale.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PreferenceRepository {
    int updatePreferencesCount(@Param("profileId") Long profileId,
                               @Param("fairyTaleId") Long fairyTaleId);
}