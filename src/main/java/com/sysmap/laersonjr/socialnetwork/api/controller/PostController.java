package com.sysmap.laersonjr.socialnetwork.api.controller;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.PostRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.PostResponseBodyDTO;
import com.sysmap.laersonjr.socialnetwork.domain.service.IPostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private IPostService iPostService;

    @PostMapping
    public ResponseEntity<PostResponseBodyDTO> createPost(@Valid @RequestBody PostRequestBodyDTO postRequestBodyDTO, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iPostService.createPostService(postRequestBodyDTO, request));
    }

    //TODO: Criar páginação para listar posts e posts por users
    @GetMapping
    public List<PostResponseBodyDTO> listPosts(HttpServletRequest request) {
        return ResponseEntity.ok(iPostService.postListingService(request)).getBody();
    }

    @GetMapping("/myposts")
    public List<PostResponseBodyDTO> listPostsByUser(HttpServletRequest request) {
        return ResponseEntity.ok(iPostService.listPostByUserService(request)).getBody();
    }

    @GetMapping("/{nickName}")
    public List<PostResponseBodyDTO> listPostByNickName(@PathVariable String nickName, HttpServletRequest request) {
        return ResponseEntity.ok(iPostService.listPostByNickName(nickName, request)).getBody();
    }

    @GetMapping("/id/{postId}")
    public ResponseEntity<PostResponseBodyDTO> getPostbyID(@PathVariable UUID postId, HttpServletRequest request){
        return ResponseEntity.ok(iPostService.findPostByIdService(postId, request));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseBodyDTO> updatePost(@PathVariable UUID postId, @Valid @RequestBody PostRequestBodyDTO postRequestBodyDTO,
                                                          HttpServletRequest request){
        return ResponseEntity.ok(iPostService.updatePostService(postId, postRequestBodyDTO, request));
    }

    @PutMapping("/{postId}/like")
    public ResponseEntity<PostResponseBodyDTO> likeOrUnlike(@PathVariable UUID postId, HttpServletRequest request){
        return ResponseEntity.ok(iPostService.likeOrUnlikeInPost(postId, request));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID postId, HttpServletRequest request){
        iPostService.deletePostService(postId, request);
        return ResponseEntity.noContent().build();
    }


}
