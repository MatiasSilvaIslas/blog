package com.matias.blog.mapper;

import com.matias.blog.dto.CommentDTO;
import com.matias.blog.entities.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public CommentDTO convertCommentEntityToCommentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setName(comment.getName());
        commentDTO.setEmail(comment.getEmail());
        commentDTO.setBody(comment.getBody());
        return commentDTO;
    }

    public Comment convertCommentDTOToCommentEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setBody(commentDTO.getBody());
        return comment;
    }

}
