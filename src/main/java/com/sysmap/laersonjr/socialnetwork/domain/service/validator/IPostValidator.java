package com.sysmap.laersonjr.socialnetwork.domain.service.validator;

import com.sysmap.laersonjr.socialnetwork.domain.entity.Post;
import jakarta.servlet.http.HttpServletRequest;

public interface IPostValidator {

    boolean isPostNotOwnedByUser(HttpServletRequest request, Post findPost);

}
