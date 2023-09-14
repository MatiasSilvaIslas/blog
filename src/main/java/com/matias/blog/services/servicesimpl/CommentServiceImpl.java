package com.matias.blog.services.servicesimpl;

import com.matias.blog.dto.CommentDTO;
import com.matias.blog.entities.Article;
import com.matias.blog.entities.Comment;
import com.matias.blog.exceptions.ResourceNotFoundException;
import com.matias.blog.mapper.CommentMapper;
import com.matias.blog.repositories.ArticleRepository;
import com.matias.blog.repositories.CommentRespository;
import com.matias.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRespository commentRespository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public CommentDTO createComment(long articleId, CommentDTO commentDTO) {
        Comment comment = commentMapper.convertCommentDTOToCommentEntity(commentDTO);

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article", "id", articleId));

        comment.setArticle(article);
        Comment newComment = commentRespository.save(comment);

        return commentMapper.convertCommentEntityToCommentDTO(newComment);
    }
}
