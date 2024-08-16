package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;
    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {

        {   //1.입력 데이터 준비
        Long articleid = 4L;
        //2.실제 데이터
        List<Comment> comments = commentRepository.findByArticleId(articleid);
        //3.예상 데이터
        Article article = new Article(4L, "당신의 인생 영화는?", "댓글 고");
        Comment a = new Comment(1L, article, "park", "쇼생크");
        Comment b = new Comment(2L, article, "kim", "보헤미안");
        List<Comment> expected = Arrays.asList(a, b);
        //4.비교 및 검증
        assertEquals(comments.toString(), expected.toString(), "4번 게시글 모두 출력!");
    }
        {   //1.입력 데이터 준비
            Long articleid = 1L;
            //2.실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleid);
            //3.예상 데이터
            Article article = new Article(1L, "가가가가", "1111");
            List<Comment> expected = Arrays.asList();
            //4.비교 및 검증
            assertEquals(comments.toString(), expected.toString(), "1번 게시글  댓글 없음!");
        }

    }

    @Test
    @DisplayName("닉네임으로 검색")
    void findByArticleNickname() {
        //1.입력 데이터 준비
        String nickname="park";
        //2.실제 데이터
        List<Comment> comments=commentRepository.findByArticleNickname(nickname);

        //3.예상 데이터
        Comment a=new Comment(1L,new Article(4L,"당신의 인생 영화는?","댓글 고"),nickname,"쇼생크");
        Comment b=new Comment(3L,new Article(5L,"당신의 소울 푸드는","댓글 고"),nickname,"치킨");
        Comment c=new Comment(5L,new Article(6L,"당신의 취미는","댓글 고"),nickname,"배드민턴");
        List<Comment> expected= Arrays.asList(a,b,c);
        //4.비교 및 검증
        assertEquals(comments.toString(),expected.toString(),"park의 모든 댓글 출력");
    }
}