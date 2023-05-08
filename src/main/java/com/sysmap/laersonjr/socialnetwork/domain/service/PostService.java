package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.PostRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.PostResponseBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UserResume;
import com.sysmap.laersonjr.socialnetwork.domain.exception.ForbiddenActionException;
import com.sysmap.laersonjr.socialnetwork.domain.exception.PostNotFoundException;
import com.sysmap.laersonjr.socialnetwork.domain.model.Comment;
import com.sysmap.laersonjr.socialnetwork.domain.model.Like;
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
            post.setUser(iModelMapperDTOConverter.convertToModelDTO(user, UserResume.class));
            post.setCreatedDate();
            post.setId();
            postRepository.save(post);
            return iModelMapperDTOConverter.convertToModelDTO(post, PostResponseBodyDTO.class);
        }
        return null;
    }

    @Override
    public List<PostResponseBodyDTO> postListingService(HttpServletRequest request) {
        iAuthenticationService.validadTokenByRequest(request);
        return iModelMapperDTOConverter.convertToModelListDTO(postRepository.findAll(), PostResponseBodyDTO.class);
    }

    @Override
    public List<PostResponseBodyDTO> listPostByUserService(HttpServletRequest request) {
        User authenticatedUser = iAuthenticationService.getAuthenticatedUser(request);
        List<Post> posts = postRepository.findByUserId(authenticatedUser.getId());
        return iModelMapperDTOConverter.convertToModelListDTO(posts, PostResponseBodyDTO.class);
    }

    @Override
    public List<PostResponseBodyDTO> listPostByNickName(String nickName, HttpServletRequest request) {
        iAuthenticationService.validadTokenByRequest(request);
        return iModelMapperDTOConverter.convertToModelListDTO(postRepository.findByUserNickName(nickName), PostResponseBodyDTO.class);
    }

    @Override
    public PostResponseBodyDTO findPostByIdService(UUID postId, HttpServletRequest request) {
        Post findPost = searchPostById(postId);
        iAuthenticationService.validadTokenByRequest(request);
        return iModelMapperDTOConverter.convertToModelDTO(findPost, PostResponseBodyDTO.class);
    }

    @Override
    public PostResponseBodyDTO updatePostService(UUID postId, PostRequestBodyDTO postRequestBodyDTO, HttpServletRequest request) {
        Post findPost = searchPostById(postId);
        if (isPostNotOwnedByUser(request, findPost)) {
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
        if (isPostNotOwnedByUser(request, findPost)) {
            throw new ForbiddenActionException();
        }
        postRepository.deleteById(postId);
    }

    public void saveCommentInPost(Comment newComment, UUID idPost) {
        Post findPost = searchPostById(idPost);
        findPost.addComment(newComment);
        postRepository.save(findPost);
    }

    @Override
    public void removeCommentFromPost(Post postComments, Comment comment) {
        postComments.getComments().remove(comment);
        postRepository.save(postComments);
    }

    @Override
    public void updateCommentFromPost(Post postComments, Comment comment) {
        for (int i = 0; i < postComments.getComments().size(); i++) {
            if (postComments.getComments().get(i).getId().equals(comment.getId())) {
                postComments.getComments().get(i).setCommentary(comment.getCommentary());
                postComments.getComments().get(i).setUpdatedDate();
                postRepository.save(postComments);
                break;
            }
        }
    }

    @Override
    public PostResponseBodyDTO likeOrUnlikeInPost(UUID idPost, HttpServletRequest request) {
        User user = iAuthenticationService.getAuthenticatedUser(request);
        UserResume userResume = iModelMapperDTOConverter.convertToModelDTO(user, UserResume.class);
        Post post = searchPostById(idPost);
        List<Like> likes = post.getLikes();
        boolean userLikedPost = false;
        for (Like like : likes) {
            if (like.getUser().getId().equals(user.getId())) {
                likes.remove(like);
                userLikedPost = true;
                break;
            }
        }
        if (!userLikedPost) {
            Like like = new Like();
            like.setUser(userResume);
            like.setCreatedDate();
            likes.add(like);
        }
        postRepository.save(post);
        PostResponseBodyDTO postResponseBodyDTO = iModelMapperDTOConverter.convertToModelDTO(post, PostResponseBodyDTO.class);
        postResponseBodyDTO.setLikeCounts(post.getLikes().size());
        return postResponseBodyDTO;
    }

    private boolean isPostNotOwnedByUser(HttpServletRequest request, Post findPost) {
        return !findPost.getUser().getId().equals(iAuthenticationService.getAuthenticatedUser(request).getId());
    }


    public Post searchPostById(UUID postId) {
        return postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException());
    }


}
