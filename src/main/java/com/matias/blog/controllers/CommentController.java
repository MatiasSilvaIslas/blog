package com.matias.blog.controllers;

import com.matias.blog.dto.CommentDTO;
import com.matias.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("articles/{articleId}/comments")
    public List<CommentDTO> listCommentsByArticle(@PathVariable (value = "articleId") Long articleId){
        return commentService.getCommentsByArticleId(articleId);
    }

    @GetMapping("articles/{articleId}/comments/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable (value = "articleId") Long articleId, @PathVariable (value = "id") Long id){
        CommentDTO commentDTO = commentService.getCommentById(id, articleId);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @PostMapping("articles/{articleId}/comments")
    public ResponseEntity<CommentDTO> saveComment(@PathVariable (value = "articleId") Long articleId, @RequestBody CommentDTO commentDTO){
        return new ResponseEntity<>(commentService.createComment(articleId, commentDTO), HttpStatus.CREATED);
    }

    @PutMapping("articles/{articleId}/comments/{id}")
    public ResponseEntity<CommentDTO> updateCommentById(@PathVariable (value = "articleId") Long articleId, @PathVariable (value = "id") Long id, @RequestBody CommentDTO commentDTO){
        CommentDTO updatedComment = commentService.updateComment(articleId,id, commentDTO);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }
}
