package kkk.dainyong.tale.repository;

import kkk.dainyong.tale.model.dto.UpdatePreferenceDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PreferenceRepository {
    void updatePreferencesCount(UpdatePreferenceDTO updatePreferenceDTO);
}
