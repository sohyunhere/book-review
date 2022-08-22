package com.example.bookreview.board.service;

import com.example.bookreview.board.model.Comments;
import com.example.bookreview.board.model.CommentsDto;
import com.example.bookreview.board.repo.CommentRepo;
import com.example.bookreview.user.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepo commentRepo;
    private final BoardService boardService;

    //댓글 작성
    @Transactional
    public Long registerComment(CommentsDto dto, Member member){
        Date today = new Date();
        dto.setWrittenDate(today);
        dto.setMember(member);
        dto.setPost(boardService.findPostBypostId(dto.getPostId()));

        Comments comment = dto.toEntity();
        Comments saveComm = commentRepo.save(comment);

        return saveComm.getCommentId();
    }

    //댓글 삭제
    @Transactional
    public Long deleteComment(Long commentId){
        commentRepo.deleteById(commentId);
        return commentId;
    }

    //postId에 따른 댓글 리스트 불러오기
    public List<Comments> findListByPostId(Long postId){
        return commentRepo.findCommentsByPostPostId(postId);
    }
}
