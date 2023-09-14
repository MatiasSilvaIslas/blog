package com.matias.blog.dto;

import com.matias.blog.entities.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotEmpty
    @Size(min = 2, message = "The article title should have at least two characters.")
    private String title;
    @NotEmpty
    @Size(min = 10, message = "The article description should have at least ten characters.")
    private String description;
    @NotEmpty
    private String content;
    private Set<Comment> comments;
}
