package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    CommentService commentService;
    //1.댓글 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        List<CommentDto> dtos=commentService.comments(articleId);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);

    }
    //2.댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto){
        CommentDto commentDto=commentService.create(articleId,dto);


        return ResponseEntity.status(HttpStatus.OK).body(commentDto);

    }
    //3.댓글 수정
    @PatchMapping("/api/commnets/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,@RequestBody CommentDto dto){
        CommentDto commentDto =commentService.update(id,dto);


        return ResponseEntity.status(HttpStatus.OK).body(commentDto);
    }
    //4.댓글 삭제
    @DeleteMapping("/api/comment/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id){
        CommentDto commentDto =commentService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(commentDto);

    }

}
