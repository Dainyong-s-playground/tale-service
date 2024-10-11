package kkk.dainyong.tale.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kkk.dainyong.tale.model.History;

@Mapper
public interface HistoryRepository {
	List<History> getRecentlyWatchedContent(@Param("profileId") Long profileId);

	void insertHistory(History history);

	void updateHistory(History history);

	void deleteHistory(@Param("profileId") Long profileId, @Param("fairyTaleId") Long fairyTaleId);
	History getLatestHistory(@Param("profileId") Long profileId, @Param("fairyTaleId") Long fairyTaleId);

}