package kkk.dainyong.tale.repository;

import java.util.List;
import java.util.Optional;

import kkk.dainyong.tale.model.dto.PastDataDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kkk.dainyong.tale.model.History;

@Mapper
public interface HistoryRepository {
	List<History> getRecentlyWatchedContent(@Param("profileId") Long profileId);
	List<PastDataDTO> selectHistoriesByProfileId(@Param("profileId") Long profileId);

	void insertHistory(History history);

	void updateHistory(History history);

	void deleteHistory(@Param("profileId") Long profileId, @Param("fairyTaleId") Long fairyTaleId);

	History getLatestHistory(@Param("profileId") Long profileId, @Param("fairyTaleId") Long fairyTaleId);

	History getHistoryByProfileIdAndFairyTaleId(@Param("profileId") Long profileId, @Param("fairyTaleId") Long fairyTaleId);


}