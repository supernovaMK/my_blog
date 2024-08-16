package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
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
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());

    }
}
