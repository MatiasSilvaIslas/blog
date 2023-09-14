package com.matias.blog.services.servicesimpl;

import com.matias.blog.dto.ArticleDTO;
import com.matias.blog.dto.ArticleResponse;
import com.matias.blog.entities.Article;
import com.matias.blog.exceptions.ResourceNotFoundException;
import com.matias.blog.mapper.ArticleMapper;
import com.matias.blog.repositories.ArticleRepository;
import com.matias.blog.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {
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

    @Override
    public ArticleResponse getAllAtricles(int pageNumber, int pageSize,String orderBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(orderBy).ascending():Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Article> articles = articleRepository.findAll(pageable);

        List<Article> articlesList = articles.getContent();
        List<ArticleDTO> content =  articlesList.stream().map(article -> articleMapper.convertArticleEntityToArticleDTO(article)).collect(Collectors.toList());

        ArticleResponse articleResponse = new ArticleResponse();
        articleResponse.setContent(content);
        articleResponse.setPageNumber(articles.getNumber());
        articleResponse.setPageSize(articles.getSize());
        articleResponse.setTotalElements(articles.getTotalElements());
        articleResponse.setTotalPages(articles.getTotalPages());
        articleResponse.setLast(articles.isLast());

        return  articleResponse;
    }

    @Override
    public ArticleDTO getArticleById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article", "id", id));
        return articleMapper.convertArticleEntityToArticleDTO(article);
    }

    @Override
    public ArticleDTO updateArticle(ArticleDTO articleDTO, long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article", "id", id));

        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setDescription(articleDTO.getDescription());

        Article updatedArticle = articleRepository.save(article);
        return articleMapper.convertArticleEntityToArticleDTO(updatedArticle);
    }

    @Override
    public void deleteArticle(long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article", "id", id));
        articleRepository.delete(article);
    }
}
