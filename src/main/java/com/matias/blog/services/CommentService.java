package com.matias.blog.services;

import com.matias.blog.dto.CommentDTO;

public interface CommentService {
    public CommentDTO createComment(long articleId, CommentDTO commentDTO);
}
