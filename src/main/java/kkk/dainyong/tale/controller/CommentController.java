package kkk.dainyong.tale.controller;

import kkk.dainyong.tale.model.dto.CommentsDTO;
import kkk.dainyong.tale.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;



    @GetMapping("/{profileId}")
    public ResponseEntity<List<CommentsDTO>> getCommentsData(@PathVariable Long profileId) {
        // 서비스 계층에서 병합된 데이터를 가져옴
        List<CommentsDTO> result = commentService.getComments(profileId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/saveComment")
    public ResponseEntity<String> createComment(@RequestBody CommentsDTO comment) {
        // 댓글 저장 로직 호출
        commentService.saveComment(comment);
        return ResponseEntity.ok("댓글이 성공적으로 저장되었습니다.");
    }
}
