package com.matias.blog.services;

import com.matias.blog.dto.ArticleDTO;

import java.util.List;

public interface ArticleService {
    public ArticleDTO createArticle(ArticleDTO articleDTO);
    public List<ArticleDTO> getAllAtricles();
}
