package com.sysmap.laersonjr.socialnetwork.domain.service.validator;

import com.sysmap.laersonjr.socialnetwork.domain.entity.Comment;
import jakarta.servlet.http.HttpServletRequest;

public interface ICommentValidator {

    boolean isCommentNotOwnedByUser(HttpServletRequest request, Comment comment);

}
