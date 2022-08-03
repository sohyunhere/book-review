package com.example.bookreview.board.service;

import com.example.bookreview.board.model.Post;
import com.example.bookreview.board.model.PostDto;
import com.example.bookreview.board.repo.BoardRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepo boardRepo;

    //글 작성 등록
    public Long registerPost(PostDto dto, Long userId){
        System.out.println(dto.toString());
        Date today = new Date();
        System.out.println(today);
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        java.util.Date d = java.sql.Date.valueOf(df.format(today));
        dto.setUserId(userId);
        dto.setWrittenDate(today);
        System.out.println(dto.toString());
        Post post = dto.toEntity();
        Post post1 = boardRepo.save(post);
        System.out.println(post1.getPostId()+"DDd");

        return -1L;
    }
    //글 목록 다 가져가기
    public List<Post> findAll(){
        return boardRepo.findAll();
    }
}
