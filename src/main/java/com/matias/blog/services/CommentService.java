package com.matias.blog.services;

import com.matias.blog.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO createComment(Long articleId, CommentDTO commentDTO);
    List<CommentDTO> getCommentsByArticleId(Long articleId);
    CommentDTO getCommentById(Long commentId, Long articleId);
    CommentDTO updateComment(Long articleId, Long commentId, CommentDTO commentDTO);
}
