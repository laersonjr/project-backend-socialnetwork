package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.PostRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.PostResponseBodyDTO;
import com.sysmap.laersonjr.socialnetwork.domain.model.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.UUID;

public interface IPostService {
    PostResponseBodyDTO createPostService(PostRequestBodyDTO postRequestBodyDTO, HttpServletRequest request);

    List<PostResponseBodyDTO> postListingService();

    List<PostResponseBodyDTO> listPostByUserService(HttpServletRequest request);

    List<PostResponseBodyDTO> listPostByNickName(String nickName);

    PostResponseBodyDTO findPostByIdService(UUID postId);
}
