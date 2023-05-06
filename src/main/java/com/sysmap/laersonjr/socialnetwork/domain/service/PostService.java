package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.PostRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.PostResponseBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UserResumePostDTO;
import com.sysmap.laersonjr.socialnetwork.domain.exception.ForbiddenActionException;
import com.sysmap.laersonjr.socialnetwork.domain.exception.PostNotFoundException;
import com.sysmap.laersonjr.socialnetwork.domain.model.Comment;
import com.sysmap.laersonjr.socialnetwork.domain.model.Post;
import com.sysmap.laersonjr.socialnetwork.domain.model.User;
import com.sysmap.laersonjr.socialnetwork.domain.repository.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private IAuthenticationService iAuthenticationService;

    @Override
    public PostResponseBodyDTO createPostService(PostRequestBodyDTO postRequestBodyDTO, HttpServletRequest request) {

        Post post = iModelMapperDTOConverter.convertToEntity(postRequestBodyDTO, Post.class);
        User user = iAuthenticationService.getAuthenticatedUser(request);
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
        User authenticatedUser = iAuthenticationService.getAuthenticatedUser(request);
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

    @Override
    public PostResponseBodyDTO updatePostService(UUID postId, PostRequestBodyDTO postRequestBodyDTO, HttpServletRequest request){
        Post findPost = searchPostById(postId);
        if(isPostNotOwnedByUser(request, findPost)){
            throw new ForbiddenActionException();
        }
        BeanUtils.copyProperties(postRequestBodyDTO, findPost, "id");
        findPost.setUpdatedDate();
        postRepository.save(findPost);
        return iModelMapperDTOConverter.convertToModelDTO(findPost, PostResponseBodyDTO.class);
    }

    @Override
    public void deletePostService(UUID postId, HttpServletRequest request) {
        Post findPost = searchPostById(postId);
        if(isPostNotOwnedByUser(request, findPost)){
            throw new ForbiddenActionException();
        }
        postRepository.deleteById(postId);
    }

    public void saveCommentInPost(Comment newComment, UUID idPost){
        Post findPost = searchPostById(idPost);
        findPost.addComment(newComment);
        postRepository.save(findPost);
    }

    private boolean isPostNotOwnedByUser(HttpServletRequest request, Post findPost) {
        return !findPost.getUser().getId().equals(iAuthenticationService.getAuthenticatedUser(request).getId());
    }



    public Post searchPostById(UUID postId){
        return postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException());
    }


}
