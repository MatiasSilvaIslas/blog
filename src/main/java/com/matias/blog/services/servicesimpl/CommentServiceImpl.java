package com.matias.blog.services.servicesimpl;

import com.matias.blog.dto.CommentDTO;
import com.matias.blog.entities.Article;
import com.matias.blog.entities.Comment;
import com.matias.blog.exceptions.BlogAppException;
import com.matias.blog.exceptions.ResourceNotFoundException;
import com.matias.blog.mapper.CommentMapper;
import com.matias.blog.repositories.ArticleRepository;
import com.matias.blog.repositories.CommentRepository;
import com.matias.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public CommentDTO createComment(Long articleId, CommentDTO commentDTO) {
        Comment comment = commentMapper.convertCommentDTOToCommentEntity(commentDTO);

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article", "id", articleId));

        comment.setArticle(article);
        Comment newComment = commentRepository.save(comment);

        return commentMapper.convertCommentEntityToCommentDTO(newComment);
    }

    @Override
    public List<CommentDTO> getCommentsByArticleId(Long articleId) {
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        return comments.stream().map(comment -> commentMapper.convertCommentEntityToCommentDTO(comment))
                .collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentById(Long commentId, Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article", "id", articleId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getArticle().getId().equals(article.getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "The comment does not belong to the article");
        }
        return commentMapper.convertCommentEntityToCommentDTO(comment);
    }
}
