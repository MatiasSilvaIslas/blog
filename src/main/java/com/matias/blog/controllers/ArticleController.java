package com.matias.blog.controllers;

import com.matias.blog.dto.ArticleDTO;
import com.matias.blog.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleDTO> saveArticle(@RequestBody ArticleDTO articleDTO){
        return new ResponseEntity<>(articleService.createArticle(articleDTO), HttpStatus.CREATED);
    }

    @GetMapping()
    public List<ArticleDTO> listArticles(){
        return articleService.getAllAtricles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable(name = "id") long id){
        return  ResponseEntity.ok(articleService.getArticleById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDTO> updateArticle(@RequestBody ArticleDTO articleDTO,
    @PathVariable(name = "id") long id){
        ArticleDTO responseArticle = articleService.updateArticle(articleDTO, id);
        return new ResponseEntity<>(responseArticle, HttpStatus.OK);
    }
}
