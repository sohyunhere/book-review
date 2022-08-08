package com.example.bookreview.board.service;

import com.example.bookreview.board.model.Category;
import com.example.bookreview.board.model.Post;
import com.example.bookreview.board.model.PostDto;
import com.example.bookreview.board.repo.BoardRepo;
import com.example.bookreview.board.repo.CategoryRepo;
import com.example.bookreview.user.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepo boardRepo;
    private final CategoryRepo categoryRepo;
    //글 작성 등록
    @Transactional
    public Long registerPost(PostDto dto, Member user){

        Date today = new Date();
        dto.setMember(user);
        dto.setWrittenDate(today);
        dto.setViewCount(1L);
        Optional<Category> category = categoryRepo.findByCategoryId(dto.getCategoryId());

        if(category.isPresent()){
            dto.setCategory(category.get());
            Post post = dto.toEntity();
            Post post1 = boardRepo.save(post);

            return post1.getPostId();
        }

        return -1L;
    }
    //글 목록 다 가져가기
    public List<Post> findAll(){
        return boardRepo.findAll();
    }

    @Transactional
    //postId에 해당하는 post객체 가져오기
    public Post findPostBypostId(Long id){
        Optional<Post> result =  boardRepo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        return null;
    }

    //조회수 올리기
    @Transactional
    public void updateVisit(Long id, Long viewCount){
        Post post = boardRepo.findById(id).orElseThrow(()->
                new IllegalStateException("해당 게시글이 존재하지 않습니다."));

        post.updateViewCount(viewCount);
    }

    //게시글 삭제하기
    public void deletePostById(Long id){
        boardRepo.deleteById(id);
    }
}
