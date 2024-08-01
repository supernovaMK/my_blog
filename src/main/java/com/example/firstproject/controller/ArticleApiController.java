package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleApiController {
    @Autowired
    ArticleRepository articleRepository;

    //Get
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
            return articleRepository.findById(id).orElse(null);

    }
    //Post
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm form){
        Article articleEntity=form.toEntity();
        return articleRepository.save(articleEntity);
    }

    //Patch

    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,@RequestBody ArticleForm dto){
        Article articleEntity=dto.toEntity();
        Article target=articleRepository.findById(id).orElse(null);
        if(target==null || id!=target.getId()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        target.patch(articleEntity);
        Article updated= articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }



    @DeleteMapping("api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article target=articleRepository.findById(id).orElse(null);
        if(target==null || id!= target.getId()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
