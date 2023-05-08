package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.CommentRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.CommentResponseBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UserResume;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UserResumeCommentDTO;
import com.sysmap.laersonjr.socialnetwork.domain.exception.CommentNotFoundException;
import com.sysmap.laersonjr.socialnetwork.domain.exception.ForbiddenActionException;
import com.sysmap.laersonjr.socialnetwork.domain.model.Comment;
import com.sysmap.laersonjr.socialnetwork.domain.model.Like;
import com.sysmap.laersonjr.socialnetwork.domain.model.Post;
import com.sysmap.laersonjr.socialnetwork.domain.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
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

    @Override
    public CommentResponseBodyDTO updateCommentService(UUID idPost, UUID idComment, HttpServletRequest request, CommentRequestBodyDTO commentRequestBodyDTO) {
        Post postComments = iPostService.searchPostById(idPost);
        Comment comment = getCommentById(idComment, postComments.getComments());
        if (isCommentNotOwnedByUser(request, comment)) {
            throw new ForbiddenActionException();
        }
        comment.setCommentary(commentRequestBodyDTO.getCommentary());
        iPostService.updateCommentFromPost(postComments, comment);
        return iModelMapperDTOConverter.convertToModelDTO(comment, CommentResponseBodyDTO.class);
    }

    @Override
    public CommentResponseBodyDTO likeOrUnlikeInComment(UUID idPost, UUID idComment, HttpServletRequest request) {
        User user = iAuthenticationService.getAuthenticatedUser(request);
        UserResume userResume = iModelMapperDTOConverter.convertToModelDTO(user, UserResume.class);
        Post postComments = iPostService.searchPostById(idPost);
        Comment comment = getCommentById(idComment, postComments.getComments());
        List<Like> likes = comment.getLikes();
        boolean userLikedPost = false;
        Iterator<Like> iterator = likes.iterator();
        while (iterator.hasNext()) {
            Like like = iterator.next();
            if (like.getUser().getId().equals(user.getId())) {
                iterator.remove();
                userLikedPost = true;
                break;
            }
        }
        if (!userLikedPost) {
            Like like = new Like();
            like.setUser(userResume);
            like.setCreatedDate();
            likes.add(like);
        }

        iPostService.updateCommentFromPost(postComments, comment);
        CommentResponseBodyDTO commentResponseBodyDTO = iModelMapperDTOConverter.convertToModelDTO(postComments, CommentResponseBodyDTO.class);
        commentResponseBodyDTO.setLikeCounts(comment.getLikes().size());

        return commentResponseBodyDTO;
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
