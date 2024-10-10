package kkk.dainyong.tale.service;

import kkk.dainyong.tale.model.dto.FairyTaleDataDTO;
import kkk.dainyong.tale.repository.FairyTaleDataRepository;
import kkk.dainyong.tale.repository.FairyTaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class FairyTalePlayerService {

    private final FairyTaleRepository fairyTaleRepository;
    private final FairyTaleDataRepository fairyTaleDataRepository;

    @Transactional(readOnly = true)
    public FairyTaleDataDTO getFairyTaleData(Long fairyTaleNumber) {
        // 존재하는 동화인지 체크
        if(fairyTaleRepository.findById(fairyTaleNumber) == null) throw new IllegalStateException("존재하지 않는 동화책입니다.");

        return fairyTaleDataRepository.getFairyTaleData(fairyTaleNumber);
    }
}
