package com.sysmap.laersonjr.socialnetwork.domain.service.validator;

import com.sysmap.laersonjr.socialnetwork.domain.entity.Comment;
import com.sysmap.laersonjr.socialnetwork.domain.service.IAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentValidator implements ICommentValidator {

    @Autowired
    private IAuthenticationService iAuthenticationService;

    @Override
    public boolean isCommentNotOwnedByUser(HttpServletRequest request, Comment comment) {
        return !comment.getUser().getId().equals(iAuthenticationService.getAuthenticatedUser(request).getId());
    }
}
