package com.matias.blog.services;

import com.matias.blog.dto.ArticleDTO;
import com.matias.blog.entities.Article;
import com.matias.blog.mapper.ArticleMapper;
import com.matias.blog.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleMapper articleMapper;
    @Override
    public ArticleDTO createArticle(ArticleDTO articleDTO) {
        Article article = articleMapper.convertArticleDTOToArticleEntity(articleDTO);
        Article publishedArticle = articleRepository.save(article);
        return articleMapper.convertArticleEntityToArticleDTO(publishedArticle);
    }
}
