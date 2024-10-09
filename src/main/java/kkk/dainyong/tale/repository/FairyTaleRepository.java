package kkk.dainyong.tale.repository;

import kkk.dainyong.tale.model.FairyTale;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author dae
 * @date 2024-10-10
 */
@Mapper
public interface FairyTaleRepository {
	List<FairyTale> findTop5ByOrderByViewsDesc();
}