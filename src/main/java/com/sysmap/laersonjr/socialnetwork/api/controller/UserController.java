package com.sysmap.laersonjr.socialnetwork.api.controller;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.UserRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UserResponseBodyDTO;
import com.sysmap.laersonjr.socialnetwork.domain.repository.UserRepository;
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

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId, HttpServletRequest request){
        iUserService.deleteUserService(userId, request);
        return ResponseEntity.noContent().build();
    }

}
