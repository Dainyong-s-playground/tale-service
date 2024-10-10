package kkk.dainyong.tale.service;

import kkk.dainyong.tale.model.dto.CommentsDTO;
import kkk.dainyong.tale.repository.CommentRepository;
import kkk.dainyong.tale.repository.FairyTaleRepository;
import kkk.dainyong.tale.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
      this.commentRepository=commentRepository;
    }
    // 댓글 저장 메서드
    public void saveComment(CommentsDTO comment) {
        commentRepository.insertComment(comment);
    }

    public List<CommentsDTO> getComments(Long profileId) {
        List<CommentsDTO> commentsList = commentRepository.selectCommentsByProfileId(profileId);

        return commentsList;
    }
}
