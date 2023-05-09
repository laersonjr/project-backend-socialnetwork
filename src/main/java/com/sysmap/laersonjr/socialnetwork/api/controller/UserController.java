package com.sysmap.laersonjr.socialnetwork.api.controller;

import com.sysmap.laersonjr.socialnetwork.api.dto.request.InviteRequest;
import com.sysmap.laersonjr.socialnetwork.api.dto.request.UserRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.dto.response.UserResponseBodyDTO;
import com.sysmap.laersonjr.socialnetwork.domain.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @PostMapping
    public ResponseEntity<UserResponseBodyDTO> createUser(@Valid @RequestBody UserRequestBodyDTO userRequestBodyDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(iUserService.createUserService(userRequestBodyDTO));
    }

    @GetMapping
    public Page<UserResponseBodyDTO> listUsers(@RequestParam(required = false, defaultValue = "") String nickName, Pageable pageable, HttpServletRequest request) {
        return iUserService.userListingService(nickName, pageable, request);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseBodyDTO> findUserById(@PathVariable UUID userId, HttpServletRequest request){
        return ResponseEntity.ok(iUserService.findUserByIdService(userId, request));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseBodyDTO> updateUser(@PathVariable UUID userId, @Valid @RequestBody UserRequestBodyDTO userRequestBodyDTO, HttpServletRequest request){
        return ResponseEntity.ok(iUserService.updateUserService(userId, userRequestBodyDTO, request));
    }

    @PutMapping("/invite/{nickName}")
    public ResponseEntity<Void> sendFriendRequest(@PathVariable String nickName, HttpServletRequest request){
        iUserService.sendFriendRequestService(nickName, request);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/invite/{nickName}/accept")
    public ResponseEntity<Void> acceptFriendRequest(@PathVariable String nickName, @Valid @RequestBody InviteRequest inviteRequest, HttpServletRequest request){
        iUserService.acceptFriendRequestService(nickName, inviteRequest ,request);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId, HttpServletRequest request){
        iUserService.deleteUserService(userId, request);
        return ResponseEntity.noContent().build();
    }

}
