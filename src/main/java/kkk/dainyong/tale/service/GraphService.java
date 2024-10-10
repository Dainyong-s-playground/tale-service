package kkk.dainyong.tale.service;


import kkk.dainyong.tale.model.Reports;
import kkk.dainyong.tale.repository.GraphRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GraphService {
    private final GraphRepository graphRepository;

    public Reports getReports(Long profileId){
        Reports reports = graphRepository.getReports(profileId);
        return reports;
    }
}
