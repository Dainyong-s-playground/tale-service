package kkk.dainyong.tale.controller;

import kkk.dainyong.tale.model.Reports;
import kkk.dainyong.tale.model.dto.PreferenceDTO;
import kkk.dainyong.tale.repository.GraphRepository;
import kkk.dainyong.tale.service.GraphService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/graph")
@Slf4j
@CrossOrigin("https://www.dainyongplayground.site")
public class GraphController {

    private final GraphService graphService;

    @GetMapping("/report/{profileId}")
    public ResponseEntity<Reports> getReportData(@PathVariable Long profileId){
        Reports reports = graphService.getReports(profileId);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/prefer/{profileId}")
    public ResponseEntity<List<PreferenceDTO>> getPreferData(@PathVariable Long profileId){
        List<PreferenceDTO> preference = graphService.getPreference(profileId);
        return ResponseEntity.ok(preference);
    }

    @PatchMapping("/totalCount/{profileId}")
    public ResponseEntity incrementTotalCount(@PathVariable Long profileId) {
        graphService.incrementTotalCount(profileId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/recordCount/{profileId}")
    public ResponseEntity incrementRecordCount(@PathVariable Long profileId) {
        graphService.incrementRecordCount(profileId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/motionCount/{profileId}")
    public ResponseEntity incrementMotionCount(@PathVariable Long profileId) {
        graphService.incrementMotionCount(profileId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/gameCount/{profileId}")
    public ResponseEntity incrementGameCount(@PathVariable Long profileId) {
        graphService.incrementGameCount(profileId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
