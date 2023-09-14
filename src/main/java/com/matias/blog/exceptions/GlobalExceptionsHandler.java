package com.matias.blog.exceptions;

import com.matias.blog.dto.DetailsError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<DetailsError> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        DetailsError detailsError = new DetailsError(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(detailsError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogAppException.class)
    public ResponseEntity<DetailsError> handleBlogAppException(BlogAppException exception, WebRequest webRequest){
        DetailsError detailsError = new DetailsError(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(detailsError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DetailsError> handleGlobalException(Exception exception, WebRequest webRequest){
        DetailsError detailsError = new DetailsError(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(detailsError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
