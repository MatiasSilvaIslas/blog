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
    @GetMapping()
    public List<ArticleDTO> listArticles(@RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize){
        return articleService.getAllAtricles(pageNumber,pageSize);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable(name = "id") long id){
        return  ResponseEntity.ok(articleService.getArticleById(id));
    }
    @PostMapping
    public ResponseEntity<ArticleDTO> saveArticle(@RequestBody ArticleDTO articleDTO){
        return new ResponseEntity<>(articleService.createArticle(articleDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDTO> updateArticle(@RequestBody ArticleDTO articleDTO,
    @PathVariable(name = "id") long id){
        ArticleDTO responseArticle = articleService.updateArticle(articleDTO, id);
        return new ResponseEntity<>(responseArticle, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable(name = "id") long id){
        articleService.deleteArticle(id);
        return new ResponseEntity<>("article successfully deleted",HttpStatus.OK);
    }
}
