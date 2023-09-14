package com.matias.blog.dto;

import com.matias.blog.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleDTO {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<Comment> comments;
}
