package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.PostRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.PostResponseBodyDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface IPostService {
    PostResponseBodyDTO createPostService(PostRequestBodyDTO postRequestBodyDTO, HttpServletRequest request);
}
