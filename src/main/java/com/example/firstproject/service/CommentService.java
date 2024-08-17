package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;


    public List<CommentDto> comments(Long articleId) {
        /*//1.댓글 조회
        List<Comment> comments =commentRepository.findByArticleId(articleId);
        //2.Dto로 변환
        List<CommentDto> commentDtos = new ArrayList<CommentDto>();
        for(int i=0;i<comments.size();i++){
            Comment comment=comments.get(i);
            CommentDto dto=CommentDto.createCommentDto(comment);
            commentDtos.add(dto);
        }*/

        return commentRepository.findByArticleId(articleId).stream()
                .map(CommentDto::createCommentDto)
                .collect(Collectors.toList());
    }
    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        //1. 게시글 조회 및 예외 발생
        Article article= articleRepository.findById(articleId).orElseThrow(()->new IllegalArgumentException("댓글 생성 실패"+"대상 게시글이 없습니다."));
        //2. 댓글 엔티티 생성
        Comment comment =Comment.createComment(dto,article);
        Comment created =commentRepository.save(comment);
        return CommentDto.createCommentDto(created);
    }
    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
    Comment target = commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("없는 댓글 입니다."));
    target.patch(dto);
    Comment updated=commentRepository.save(target);
    return CommentDto.createCommentDto(updated);

    }
    @Transactional
    public CommentDto delete(Long id) {
    Comment comment =commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("없는 게시글입니다."));
    commentRepository.delete(comment);

    return CommentDto.createCommentDto(comment);
    }
}
