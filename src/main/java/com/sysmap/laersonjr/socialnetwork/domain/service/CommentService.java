package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.CommentRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.CommentResponseBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UserResumeCommentDTO;
import com.sysmap.laersonjr.socialnetwork.domain.exception.CommentNotFoundException;
import com.sysmap.laersonjr.socialnetwork.domain.exception.ForbiddenActionException;
import com.sysmap.laersonjr.socialnetwork.domain.model.Comment;
import com.sysmap.laersonjr.socialnetwork.domain.model.Post;
import com.sysmap.laersonjr.socialnetwork.domain.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private IAuthenticationService iAuthenticationService;

    @Autowired
    private IPostService iPostService;

    @Autowired
    private IModelMapperDTOConverter iModelMapperDTOConverter;

    @Override
    public CommentResponseBodyDTO createCommentService(UUID idPost, CommentRequestBodyDTO commentRequestBodyDTO, HttpServletRequest request) {
        User authenticatedUser = iAuthenticationService.getAuthenticatedUser(request);
        if (authenticatedUser == null) {
            return null;
        }
        Comment newComment = iModelMapperDTOConverter.convertToEntity(commentRequestBodyDTO, Comment.class);
        newComment.setUser(iModelMapperDTOConverter.convertToModelDTO(authenticatedUser, UserResumeCommentDTO.class));
        newComment.setCreatedDate();
        newComment.setId();
        iPostService.saveCommentInPost(newComment, idPost);

        return iModelMapperDTOConverter.convertToModelDTO(newComment, CommentResponseBodyDTO.class);
    }

    @Override
    public void removeCommentService(UUID idPost, UUID idComment, HttpServletRequest request) {
        Post postComments = iPostService.searchPostById(idPost);
        Comment comment = getCommentById(idComment, postComments.getComments());
        if (isCommentNotOwnedByUser(request, comment)) {
            throw new ForbiddenActionException();
        }
        iPostService.removeCommentFromPost(postComments, comment);
    }

    private boolean isCommentNotOwnedByUser(HttpServletRequest request, Comment comment) {
        return !comment.getUser().getId().equals(iAuthenticationService.getAuthenticatedUser(request).getId());
    }

    private Comment getCommentById(UUID idComment, List<Comment> comments) throws CommentNotFoundException {
        for (Comment comment : comments) {
            if (comment.getId().equals(idComment)) {
                return comment;
            }
        }
        throw new CommentNotFoundException();
    }


}
