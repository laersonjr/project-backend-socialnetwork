package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.dto.request.CommentRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.dto.response.CommentResponseBodyDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public interface ICommentService {
    CommentResponseBodyDTO createCommentService(UUID idPost, CommentRequestBodyDTO commentRequestBodyDTO, HttpServletRequest request);

    void removeCommentService(UUID idPost, UUID idComment, HttpServletRequest request);

    CommentResponseBodyDTO updateCommentService(UUID idPost, UUID idComment, HttpServletRequest request, CommentRequestBodyDTO commentRequestBodyDTO);

    CommentResponseBodyDTO likeOrUnlikeInComment(UUID idPost, UUID idComment, HttpServletRequest request);
}
