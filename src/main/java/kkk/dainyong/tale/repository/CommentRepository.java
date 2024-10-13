package kkk.dainyong.tale.repository;

import kkk.dainyong.tale.model.dto.CommentsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentRepository {
    List<CommentsDTO>  selectCommentsByProfileId(@Param("profileId")Long profileId);
    void insertComment(CommentsDTO comment);
}
