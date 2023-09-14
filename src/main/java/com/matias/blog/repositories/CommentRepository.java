package com.matias.blog.repositories;

import com.matias.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
     List<Comment> findByArticleId(long articleId);
}
