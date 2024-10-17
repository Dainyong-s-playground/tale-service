package kkk.dainyong.tale.service;

import kkk.dainyong.tale.model.dto.FairyTaleDataDTO;
import kkk.dainyong.tale.model.dto.FairyTaleTtsDTO;
import kkk.dainyong.tale.repository.FairyTaleDataRepository;
import kkk.dainyong.tale.repository.FairyTaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class FairyTalePlayerService {

    private final FairyTaleRepository fairyTaleRepository;
    private final FairyTaleDataRepository fairyTaleDataRepository;
    private final String TTS_MODEL = "http://tts.dainyongplayground.site:7773";

    @Transactional(readOnly = true)
    public FairyTaleDataDTO getFairyTaleData(Long fairyTaleNumber) {
        // 존재하는 동화인지 체크
        if(fairyTaleRepository.findById(fairyTaleNumber) == null) throw new IllegalStateException("존재하지 않는 동화책입니다.");

        return fairyTaleDataRepository.getFairyTaleData(fairyTaleNumber);
    }

    public byte[] generateTTS(FairyTaleTtsDTO fairyTaleTtsDTO) {
        RestTemplate restTemplate = new RestTemplate();

        // header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // body
        Map<String, String> body = new HashMap<>();
        body.put("sentence", fairyTaleTtsDTO.getSentence());
        body.put("language", fairyTaleTtsDTO.getLanguage());

        // message
        HttpEntity<Map<String, String>> requestMessage = new HttpEntity<>(body, headers);

        // request
        return restTemplate.postForObject(TTS_MODEL + "/generate", requestMessage, byte[].class);
    }
}
