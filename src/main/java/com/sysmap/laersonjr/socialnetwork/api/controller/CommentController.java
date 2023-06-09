package com.sysmap.laersonjr.socialnetwork.api.controller;

import com.sysmap.laersonjr.socialnetwork.api.dto.request.CommentRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.dto.response.CommentResponseBodyDTO;
import com.sysmap.laersonjr.socialnetwork.domain.service.ICommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts/{idPost}/comment")
public class CommentController {


    @Autowired
    private ICommentService iCommentService;

    @PostMapping
    public ResponseEntity<CommentResponseBodyDTO> createComment(@PathVariable UUID idPost, @Valid @RequestBody CommentRequestBodyDTO commentRequestBodyDTO
            , HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(iCommentService.createCommentService(idPost, commentRequestBodyDTO, request));
    }

    @PutMapping("/{idComment}")
    public ResponseEntity<CommentResponseBodyDTO> updateComment(@PathVariable UUID idPost, @PathVariable UUID idComment,
                                                                HttpServletRequest request, @Valid @RequestBody CommentRequestBodyDTO commentRequestBodyDTO){
        return ResponseEntity.ok(iCommentService.updateCommentService(idPost, idComment, request, commentRequestBodyDTO));
    }

    @PutMapping("/{idComment}/like")
    public ResponseEntity<CommentResponseBodyDTO> likeOrUnlike(@PathVariable UUID idPost, @PathVariable UUID idComment,
                                                            HttpServletRequest request){
        return ResponseEntity.ok(iCommentService.likeOrUnlikeInComment(idPost, idComment, request));
    }

    @DeleteMapping("/{idComment}")
    public ResponseEntity<Void> removeComment(@PathVariable UUID idPost, @PathVariable UUID idComment, HttpServletRequest request){
        iCommentService.removeCommentService(idPost, idComment, request);
        return ResponseEntity.noContent().build();
    }

}
