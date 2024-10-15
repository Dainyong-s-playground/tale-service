package kkk.dainyong.tale.service;


import kkk.dainyong.tale.model.Reports;
import kkk.dainyong.tale.model.dto.PreferenceDTO;
import kkk.dainyong.tale.repository.GraphRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GraphService {

    private final GraphRepository graphRepository;

    @Transactional(readOnly = true)
    public Reports getReports(Long profileId){
        Reports reports = graphRepository.getReports(profileId);
        return reports;
    }

    @Transactional(readOnly = true)
    public List<PreferenceDTO> getPreference(Long profileId){
        List<PreferenceDTO> preference = graphRepository.getPreferenceByProfileId(profileId);
        return preference;
    }

    public void incrementTotalCount(Long profileId) {
        graphRepository.incrementTotalCount(profileId);
    }
}
