package kkk.dainyong.tale.repository;

import kkk.dainyong.tale.model.FairyTale;
import kkk.dainyong.tale.model.Tags;
import kkk.dainyong.tale.model.dto.FairyTaleDTO;
import kkk.dainyong.tale.model.dto.SearchFairyTaleDTO;
import kkk.dainyong.tale.model.dto.TagDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchRepository {
    List<Tags> getTags();
    List<SearchFairyTaleDTO> getFairyTale();
    List<TagDTO> getFairyTaleTags();
}
