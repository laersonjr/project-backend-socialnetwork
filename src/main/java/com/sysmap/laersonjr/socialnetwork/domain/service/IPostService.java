package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.dto.request.PostRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.dto.response.PostResponseBodyDTO;
import com.sysmap.laersonjr.socialnetwork.domain.entity.Comment;
import com.sysmap.laersonjr.socialnetwork.domain.entity.Post;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.UUID;

public interface IPostService {
    PostResponseBodyDTO createPostService(PostRequestBodyDTO postRequestBodyDTO, HttpServletRequest request);

    List<PostResponseBodyDTO> postListingService(HttpServletRequest request);

    List<PostResponseBodyDTO> listPostByUserService(HttpServletRequest request);

    List<PostResponseBodyDTO> listPostByNickName(String nickName, HttpServletRequest request);

    PostResponseBodyDTO findPostByIdService(UUID postId, HttpServletRequest request);

    PostResponseBodyDTO updatePostService(UUID postId, PostRequestBodyDTO postRequestBodyDTO, HttpServletRequest request);

    void deletePostService(UUID postId, HttpServletRequest request);

    Post searchPostById(UUID postId);

    void saveCommentInPost(Comment newComment, UUID idPost);

    void removeCommentFromPost(Post postComments, Comment comment);

    void updateCommentFromPost(Post postComments, Comment comment);

    PostResponseBodyDTO likeOrUnlikeInPost(UUID idPost, HttpServletRequest request);

}
