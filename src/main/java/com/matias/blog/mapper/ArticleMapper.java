package com.matias.blog.mapper;

import com.matias.blog.dto.ArticleDTO;
import com.matias.blog.entities.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {
    public ArticleDTO convertArticleEntityToArticleDTO(Article article){
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setContent(article.getContent());
        articleDTO.setDescription(article.getDescription());
        return articleDTO;
    }

    public Article convertArticleDTOToArticleEntity(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setDescription(articleDTO.getDescription());
        return article;
    }

}
