package kkk.dainyong.tale.controller;

import kkk.dainyong.tale.model.FairyTale;
import kkk.dainyong.tale.model.Tags;
import kkk.dainyong.tale.model.dto.SearchFairyTaleDTO;
import kkk.dainyong.tale.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("tags")
    public ResponseEntity<List<String>> getTags(){
        List<String> tags = searchService.getTags();

        return ResponseEntity.ok(tags);
    }

    @GetMapping("fairytale")
    public ResponseEntity<List<SearchFairyTaleDTO>> getFairyTale(){
        List<SearchFairyTaleDTO> fairyTales = searchService.getFairyTale();
        return ResponseEntity.ok(fairyTales);
    }
}
