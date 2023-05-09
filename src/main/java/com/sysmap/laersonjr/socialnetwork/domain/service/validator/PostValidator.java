package com.sysmap.laersonjr.socialnetwork.domain.service.validator;

import com.sysmap.laersonjr.socialnetwork.domain.entity.Post;
import com.sysmap.laersonjr.socialnetwork.domain.service.IAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostValidator implements IPostValidator {

    @Autowired
    private IAuthenticationService iAuthenticationService;

    @Override
    public boolean isPostNotOwnedByUser(HttpServletRequest request, Post findPost) {
        return !findPost.getUser().getId().equals(iAuthenticationService.getAuthenticatedUser(request).getId());
    }

}
