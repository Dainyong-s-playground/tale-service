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

import kkk.dainyong.tale.model.FairyTale;
import kkk.dainyong.tale.model.History;
import kkk.dainyong.tale.model.dto.HistoryDTO;
import kkk.dainyong.tale.repository.FairyTaleRepository;
import kkk.dainyong.tale.repository.HistoryRepository;
import kkk.dainyong.tale.exception.FairyTaleNotFoundException; // 이 줄을 추가해주세요

@Service
@Transactional
public class HistoryService {
	private final HistoryRepository historyRepository;
	private final FairyTaleRepository fairyTaleRepository;
	private final CommentRepository	commentRepository;

	@Autowired
	public HistoryService(HistoryRepository historyRepository, FairyTaleRepository fairyTaleRepository, CommentRepository commentRepository) {
		this.historyRepository = historyRepository;
		this.fairyTaleRepository = fairyTaleRepository;
		this.commentRepository= commentRepository;
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

	public List<PastDataDTO> getPastData(Long profileId) {
		List<PastDataDTO> pastDataDTOList = historyRepository.selectHistoriesByProfileId(profileId);

		return pastDataDTOList;
	}
	public List<CommentsDTO> getComments(Long profileId) {
		List<CommentsDTO> commentsList = commentRepository.selectCommentsByProfileId(profileId);

		return commentsList;
	}
	// 필요한 경우 다른 메서드 추가
}