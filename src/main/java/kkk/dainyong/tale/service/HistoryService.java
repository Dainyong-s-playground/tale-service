package kkk.dainyong.tale.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import kkk.dainyong.tale.model.dto.CommentsDTO;
import kkk.dainyong.tale.model.dto.PastDataDTO;
import kkk.dainyong.tale.repository.CommentRepository;
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

		historyRepository.insertHistory(new History(history.getProfileId(), history.getFairyTaleId(), history.getReadDate(), history.getProgress()));
	}

}