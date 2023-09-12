package com.matias.blog.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ArticleResponse {
    private List<ArticleDTO> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private long totalPages;
    private boolean last;
}
