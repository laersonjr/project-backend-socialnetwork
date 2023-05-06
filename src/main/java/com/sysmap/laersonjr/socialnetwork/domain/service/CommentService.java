package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.CommentRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.CommentResponseBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UserResumeCommentDTO;
import com.sysmap.laersonjr.socialnetwork.domain.model.Comment;
import com.sysmap.laersonjr.socialnetwork.domain.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
