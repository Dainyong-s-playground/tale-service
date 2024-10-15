package kkk.dainyong.tale.service;

import java.util.List;
import java.util.stream.Collectors;

import kkk.dainyong.tale.model.dto.PastDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kkk.dainyong.tale.exception.FairyTaleNotFoundException;
import kkk.dainyong.tale.model.FairyTale;
import kkk.dainyong.tale.model.History;
import kkk.dainyong.tale.model.dto.HistoryDTO;
import kkk.dainyong.tale.repository.FairyTaleRepository;
import kkk.dainyong.tale.repository.HistoryRepository;

@Service
@Transactional
public class HistoryService {
	private final HistoryRepository historyRepository;
	private final FairyTaleRepository fairyTaleRepository;


	@Autowired
	public HistoryService(HistoryRepository historyRepository, FairyTaleRepository fairyTaleRepository) {
		this.historyRepository = historyRepository;
		this.fairyTaleRepository = fairyTaleRepository;
	}

	@Transactional(readOnly = true)
	public List<HistoryDTO> getRecentlyWatchedContent(Long profileId) {
		List<History> histories = historyRepository.getRecentlyWatchedContent(profileId);
		return histories.stream()
			.map(history -> {
				FairyTale fairyTale = fairyTaleRepository.findById(history.getFairyTaleId());
				if (fairyTale == null) {
					throw new FairyTaleNotFoundException(history.getFairyTaleId());
				}
				return HistoryDTO.from(history, fairyTale);
			})
			.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<PastDataDTO> getPastData(Long profileId) {
		List<PastDataDTO> pastDataDTOList = historyRepository.selectHistoriesByProfileId(profileId);

		return pastDataDTOList;
	}

	public void insertHistory(History history) {
		// 존재하는 동화인지 확인
		if(fairyTaleRepository.findById(history.getFairyTaleId()) == null) throw new IllegalStateException("존재하지 않는 동화입니다.");

		if(historyRepository.getHistoryByProfileIdAndFairyTaleId(history.getProfileId(), history.getFairyTaleId()) != null) historyRepository.updateHistory(history);
		else historyRepository.insertHistory(new History(history.getProfileId(), history.getFairyTaleId(), history.getReadDate(), history.getProgress()));
	}

	@Transactional(readOnly = true)
	public Float getProgress(Long profileId, Long fairyTaleId) {
		if(fairyTaleRepository.findById(fairyTaleId) == null) throw new IllegalStateException("존재하지 않는 동화입니다.");

		return historyRepository.getProgress(profileId, fairyTaleId);
	}
}