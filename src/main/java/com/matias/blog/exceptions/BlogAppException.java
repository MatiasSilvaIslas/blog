package com.matias.blog.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serial;
@Getter
@Setter
public class BlogAppException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    private HttpStatus status;
    private String message;

    public BlogAppException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public BlogAppException(HttpStatus status, String message, String message1) {
        this.status = status;
        this.message = message;
        this.message = message1;

    }
}
