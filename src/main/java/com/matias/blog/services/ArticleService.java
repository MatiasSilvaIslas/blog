package com.matias.blog.services;

import com.matias.blog.dto.ArticleDTO;
import com.matias.blog.dto.ArticleResponse;

public interface ArticleService {
    public ArticleDTO createArticle(ArticleDTO articleDTO);
    public ArticleResponse getAllAtricles(int pageNumber, int pageSize,String orderBy, String sortDir);
    public ArticleDTO getArticleById(Long id);
    public ArticleDTO updateArticle(ArticleDTO articleDTO, long id);
    public void deleteArticle(long id);
}
