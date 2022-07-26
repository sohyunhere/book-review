package com.example.bookreview.board.controller;

import com.example.bookreview.board.model.Category;

import com.example.bookreview.board.model.Comments;
import com.example.bookreview.board.model.Post;
import com.example.bookreview.board.service.BoardService;
import com.example.bookreview.board.service.CategoryService;
import com.example.bookreview.board.service.CommentService;
import com.example.bookreview.file.service.FileService;
import com.example.bookreview.user.model.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardWebController {
    private final CategoryService categoryService;
    private final BoardService boardService;
    private final CommentService commentService;
    private final FileService fileService;

    //글쓰기 페이지로 이동
    @GetMapping("/board/write")
    public String goWrite(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        return "board/wr_post";
    }

    //글 보기 + 조회수 1씩 올리기
    @GetMapping("/board/{postId}")
    public String postView(@PathVariable("postId") Long id, Model model){
        Post post = boardService.findPostBypostId(id);
        boardService.updateVisit(id, post.getViewCount());

        model.addAttribute("post", post);
        return "board/v_post";
    }

    //내가 작성한 글게시글 화면이동
    @GetMapping("/board/mypost")
    public String goMyPost(){
        return "member/myPost";
    }

    //수정 게시글로 이동
    @GetMapping("/board/update/{postId}")
    public String updatePost(@PathVariable("postId") Long id, Model model) throws Exception {
        Post post = boardService.findPostBypostId(id);
        model.addAttribute("post", post);
        return "board/u_post";
    }


    //내가 작성한 댓글
    @GetMapping("/board/myComment")
    public String getMyComment(Authentication auth, Model model) {
        Member member = (Member) auth.getPrincipal();
        List<Comments> comments = commentService.findListByEmail(member.getMemberEmail());
        log.info("comments: "+ comments);
        model.addAttribute("comments", comments);
        return "member/myComment";
    }

    //나의 업로드 위치
    @GetMapping("/board/myUploadLocation")
    public String getMyUploadLocation(Authentication auth, Model model){
        Member member = (Member) auth.getPrincipal();
        List<Post> posts = boardService.findPostListByUser(member.getMemberId());
        model.addAttribute("posts", posts);
        return "member/uploadLocation";
    }

    //게시판 통계
    @GetMapping("/board/chart")
    public String drawChart(){
        return "member/boardChart";
    }
}
