package com.matias.blog.controllers;

import com.matias.blog.dto.ArticleDTO;
import com.matias.blog.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleDTO> saveArticle(@RequestBody ArticleDTO articleDTO){
        return new ResponseEntity<>(articleService.createArticle(articleDTO), HttpStatus.CREATED);
    }
}
