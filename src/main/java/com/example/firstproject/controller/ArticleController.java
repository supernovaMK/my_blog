package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class ArticleController {
    @Autowired//스프링부트에서 미리 만들어둔 객체를 주입해준다.
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm(){

        return "articles/new";
    }
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){

        Article article = form.toEntity();
        Article saved=articleRepository.save(article);

        return "redirect:/articles/"+saved.getId();
    }
    @GetMapping("/articles/{id}")
    public String show(@PathVariable  Long id, Model model){


        Article articleEntity = articleRepository.findById(id).orElse(null);//원래는 findById를 하게 되면 Optional<Article>이 반환된다. 이를 해결하고자 orElse를 사용하게 된다.
        model.addAttribute("article",articleEntity);

        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){

        ArrayList<Article> articleList = articleRepository.findAll();
        model.addAttribute("articleList",articleList);
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article",articleEntity);

        return "articles/edit";
    }

    @PostMapping("/article/update")
    public String update(ArticleForm form){
        Article article = form.toEntity();
        Article target= articleRepository.findById(article.getId()).orElse(null);


        if (target!=null){
            articleRepository.save(article);
        }
        return "redirect:/aritcles/"+article.getId();

    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        Article target=articleRepository.findById(id).orElse(null);
        if(target!=null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제되었습니다.");
        }
        return "redirect:/articles";
    }
}
