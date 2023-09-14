package com.matias.blog.repositories;

import com.matias.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRespository extends JpaRepository<Comment,Long> {
}
