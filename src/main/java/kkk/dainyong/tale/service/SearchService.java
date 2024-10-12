package kkk.dainyong.tale.service;

import kkk.dainyong.tale.model.FairyTale;
import kkk.dainyong.tale.model.Tags;
import kkk.dainyong.tale.model.dto.FairyTaleDTO;
import kkk.dainyong.tale.model.dto.SearchFairyTaleDTO;
import kkk.dainyong.tale.model.dto.TagDTO;
import kkk.dainyong.tale.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class SearchService {
    private final SearchRepository searchRepository;

    public List<String> getTags(){
        List<Tags> tags = searchRepository.getTags();
        // Stream API를 사용하여 tags의 content 필드만 추출
        List<String> tag_list = tags.stream()
                .map(Tags::getContent)  // Tags 객체에서 content 필드만 추출
                .collect(Collectors.toList());  // List<String>으로 변환
        return tag_list;
    }

    public List<SearchFairyTaleDTO> getFairyTale(){
        List<SearchFairyTaleDTO> fairyTales = searchRepository.getFairyTale();
        List<TagDTO> tags = searchRepository.getFairyTaleTags();

        Map<Long, List<String>> tagMap = tags.stream()
                .collect(Collectors.groupingBy(
                        TagDTO::getFairyTaleId,
                        Collectors.mapping(TagDTO::getTags, Collectors.toList())
                ));

        // Step 4: SearchFairyTaleDTO에 태그 리스트를 추가하여 새로운 객체 생성
        List<SearchFairyTaleDTO> result = fairyTales.stream()
                .map(fairyTale -> SearchFairyTaleDTO.builder()
                        .id(fairyTale.getId())
                        .fairyTaleTitle(fairyTale.getFairyTaleTitle())
                        .fairyTaleImage(fairyTale.getFairyTaleImage())
                        .views(fairyTale.getViews())
                        .rentalPrice(fairyTale.getRentalPrice())
                        .purchasePrice(fairyTale.getPurchasePrice())
                        .description(fairyTale.getDescription())
                        .author(fairyTale.getAuthor())
                        .tag(tagMap.getOrDefault(fairyTale.getId(), new ArrayList<>()))
                        .build())
                .collect(Collectors.toList());

        return result;
    }
}
