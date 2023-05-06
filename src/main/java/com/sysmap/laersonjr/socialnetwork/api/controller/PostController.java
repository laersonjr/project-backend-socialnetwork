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
    public List<PostResponseBodyDTO> listPosts() {
        return ResponseEntity.ok(iPostService.postListingService()).getBody();
    }

    @GetMapping("/myposts")
    public List<PostResponseBodyDTO> listPostsByUser(HttpServletRequest request) {
        return ResponseEntity.ok(iPostService.listPostByUserService(request)).getBody();
    }

    @GetMapping("/{nickName}")
    public List<PostResponseBodyDTO> listPostByNickName(@PathVariable String nickName) {
        return ResponseEntity.ok(iPostService.listPostByNickName(nickName)).getBody();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseBodyDTO> getPostbyID(@PathVariable UUID postId){
        return ResponseEntity.ok(iPostService.findPostByIdService(postId));
    }

}
