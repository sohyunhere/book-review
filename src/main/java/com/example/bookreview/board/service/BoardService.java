package com.example.bookreview.board.service;

import com.example.bookreview.board.model.Post;
import com.example.bookreview.board.model.PostDto;
import com.example.bookreview.board.repo.BoardQueryRepo;
import com.example.bookreview.board.repo.BoardRepo;
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
    private final BoardQueryRepo boardQueryRepo;
    private final CategoryService categoryService;
    //글 작성 등록
    @Transactional
    public void registerPost(PostDto dto, Member user){

        Date today = new Date();
        dto.setMember(user);
        dto.setWrittenDate(today);
        dto.setViewCount(1L);

        dto.setCategory(categoryService.findCategoryById(dto.getCategoryId()));
        Post post = dto.toEntity();
        boardRepo.save(post);

    }
    //글 목록 다 가져가기
    public List<Post> findAll(){
        return boardRepo.findAll();
    }

    @Transactional
    //postId에 해당하는 post객체 가져오기
    public Post findPostBypostId(Long id){
        Optional<Post> result = Optional.ofNullable(boardRepo.findById(id).orElseThrow(() -> new IllegalStateException("post가 존재하지 않습니다.")));

        return result.get();
    }

    //조회수 올리기
    @Transactional
    public void updateVisit(Long id, Long viewCount){
        boardQueryRepo.updateView(id, viewCount+1L);
    }

    //게시글 삭제하기
    public void deletePostById(Long id){
        boardRepo.deleteById(id);
    }

    //카테고리 아이디별 게시글 리스트
    public List<Post> findListByCategoryId(Long id){
        return boardRepo.findByCategoryCategoryId(id);
    }

    //내가 작성한 글게시글 리스트 반환
    public List<Post> findListByUserId(Long userId){
        return boardRepo.findByMemberMemberId(userId);
    }
}
