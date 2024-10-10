package kkk.dainyong.tale.controller;

import kkk.dainyong.tale.model.Reports;
import kkk.dainyong.tale.repository.GraphRepository;
import kkk.dainyong.tale.service.GraphService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/graph")
public class GraphController {

    private final GraphService graphService;

    @GetMapping("/report/{profileId}")
    public ResponseEntity<Reports> getReportData(@PathVariable Long profileId){
        Reports reports = graphService.getReports(profileId);
        return ResponseEntity.ok(reports);
    }
}
