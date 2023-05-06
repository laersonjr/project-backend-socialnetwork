package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.PostRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.PostResponseBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UserResumePostDTO;
import com.sysmap.laersonjr.socialnetwork.core.security.ITokenProvide;
import com.sysmap.laersonjr.socialnetwork.domain.model.Post;
import com.sysmap.laersonjr.socialnetwork.domain.model.User;
import com.sysmap.laersonjr.socialnetwork.domain.repository.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService{

    @Autowired
    private IModelMapperDTOConverter iModelMapperDTOConverter;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ITokenProvide iTokenProvide;

    @Override
    public PostResponseBodyDTO createPostService(PostRequestBodyDTO postRequestBodyDTO, HttpServletRequest request) {

        Post post = iModelMapperDTOConverter.convertToEntity(postRequestBodyDTO, Post.class);
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.split(" ")[1];
            User user = iUserService.findUserByEmailService(iTokenProvide.getEmailFromToken(token));
            post.setUser(iModelMapperDTOConverter.convertToModelDTO(user, UserResumePostDTO.class));
            post.setCreatedDate();
            post.setId();
            postRepository.save(post);
            return iModelMapperDTOConverter.convertToModelDTO(post, PostResponseBodyDTO.class);
        }
        return null;
    }
}
