package com.matias.blog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentDTO {
    private long id;
    @NotEmpty(message = "should not be null or empty")
    private String name;
    @NotEmpty(message = "should not be null or empty")
    @Email
    private String email;
    @NotEmpty(message = "should not be null or empty")
    @Size(min = 10, message = "The comment body should have at least ten characters.")
    private String body;
}
