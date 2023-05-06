package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.PostRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.PostResponseBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UserResponseBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UserResumePostDTO;
import com.sysmap.laersonjr.socialnetwork.core.security.ITokenProvide;
import com.sysmap.laersonjr.socialnetwork.domain.exception.PostNotFoundException;
import com.sysmap.laersonjr.socialnetwork.domain.model.Post;
import com.sysmap.laersonjr.socialnetwork.domain.model.User;
import com.sysmap.laersonjr.socialnetwork.domain.repository.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostService implements IPostService {

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
        User user = getAuthenticatedUser(request);
        if (user != null) {
            post.setUser(iModelMapperDTOConverter.convertToModelDTO(user, UserResumePostDTO.class));
            post.setCreatedDate();
            post.setId();
            postRepository.save(post);
            return iModelMapperDTOConverter.convertToModelDTO(post, PostResponseBodyDTO.class);
        }
        return null;
    }

    @Override
    public List<PostResponseBodyDTO> postListingService() {
        return iModelMapperDTOConverter.convertToModelListDTO(postRepository.findAll(), PostResponseBodyDTO.class);
    }

    @Override
    public List<PostResponseBodyDTO> listPostByUserService(HttpServletRequest request) {
        User authenticatedUser = getAuthenticatedUser(request);
        if (authenticatedUser == null) {
            return null;
        }

        List<Post> posts = postRepository.findByUserId(authenticatedUser.getId());
        return iModelMapperDTOConverter.convertToModelListDTO(posts, PostResponseBodyDTO.class);
    }

    @Override
    public List<PostResponseBodyDTO> listPostByNickName(String nickName) {
        return iModelMapperDTOConverter.convertToModelListDTO(postRepository.findByUserNickName(nickName), PostResponseBodyDTO.class);
    }

    @Override
    public PostResponseBodyDTO findPostByIdService(UUID postId) {
            Post findPost = searchPostById(postId);
        return iModelMapperDTOConverter.convertToModelDTO(findPost, PostResponseBodyDTO.class);
    }

    private User getAuthenticatedUser(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.split(" ")[1];
            String email = iTokenProvide.getEmailFromToken(token);
            return iUserService.findUserByEmailService(email);
        }
        return null;
    }

    private Post searchPostById(UUID postId){
        return postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException());
    }


}
