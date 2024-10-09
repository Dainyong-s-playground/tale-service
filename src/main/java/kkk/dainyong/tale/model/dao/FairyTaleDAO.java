package kkk.dainyong.tale.model.dao;

import kkk.dainyong.tale.model.FairyTale;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * @author dae
 * @date 10/10 03:15
 */
@Mapper
public interface FairyTaleDAO {
	List<FairyTale> findTop5ByOrderByViewsDesc();
}