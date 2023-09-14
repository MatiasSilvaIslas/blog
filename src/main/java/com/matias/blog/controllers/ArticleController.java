package com.matias.blog.controllers;

import com.matias.blog.dto.ArticleDTO;
import com.matias.blog.dto.ArticleResponse;
import com.matias.blog.services.ArticleService;
import com.matias.blog.utils.AppConst;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @GetMapping()
    public ArticleResponse listArticles(@RequestParam(value = "pageNumber", defaultValue = AppConst.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
                                        @RequestParam(value = "pageSize", defaultValue = AppConst.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                        @RequestParam(value = "sortBy", defaultValue = AppConst.DEFAULT_ORDER, required = false) String orderBy,
                                        @RequestParam(value = "sortDir", defaultValue = AppConst.DEFAULT_DIRECTION, required = false) String sortDir){
        return articleService.getAllAtricles(pageNumber,pageSize,orderBy,sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable(name = "id") long id){
        return  ResponseEntity.ok(articleService.getArticleById(id));
    }
    @PostMapping
    public ResponseEntity<ArticleDTO> saveArticle(@Valid @RequestBody ArticleDTO articleDTO){
        return new ResponseEntity<>(articleService.createArticle(articleDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDTO> updateArticle(@Valid @RequestBody ArticleDTO articleDTO,
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
