package kkk.dainyong.tale.repository;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kkk.dainyong.tale.model.FairyTale;

/**
 * @author dae
 * @date 2024-10-10
 */
@Mapper
public interface FairyTaleRepository {
	List<FairyTale> findTop5ByOrderByViewsDesc();

	FairyTale findById(@Param("id") Long id);

	int canIncrementViews(@Param("fairyTaleId") Long fairyTaleId, @Param("ipAddress") String ipAddress,
		@Param("viewDate") LocalDate viewDate);

	void incrementViews(Long id);

	void insertViewLog(@Param("fairyTaleId") Long fairyTaleId, @Param("ipAddress") String ipAddress,
		@Param("viewDate") LocalDate viewDate);

	List<FairyTale> findRandomThreeFromTopTenExcludingId(@Param("excludeId") Long excludeId);

	List<Long> findTagsByFairyTaleId(@Param("fairyTaleId") Long fairyTaleId);
}
