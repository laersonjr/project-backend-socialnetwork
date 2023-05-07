package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.CommentRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.CommentResponseBodyDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public interface ICommentService {
    CommentResponseBodyDTO createCommentService(UUID idPost, CommentRequestBodyDTO commentRequestBodyDTO, HttpServletRequest request);

    void removeCommentService(UUID idPost, UUID idComment, HttpServletRequest request);

    CommentResponseBodyDTO updateCommentService(UUID idPost, UUID idComment, HttpServletRequest request, CommentRequestBodyDTO commentRequestBodyDTO);
}
