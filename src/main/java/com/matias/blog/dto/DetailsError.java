package com.matias.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailsError {
    private Date timeStamp;
    private String message;
    private String details;
}
