package com.sysmap.laersonjr.socialnetwork.api.controller;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.UserRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UserResponseBodyDTO;
import com.sysmap.laersonjr.socialnetwork.domain.repository.UserRepository;
import com.sysmap.laersonjr.socialnetwork.domain.service.IUserService;
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
    public Page<UserResponseBodyDTO> listUsers(@RequestParam(required = false, defaultValue = "") String nickName, Pageable pageable) {
        return iUserService.userListingService(nickName, pageable);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseBodyDTO> findUserById(@PathVariable UUID userId){
        return ResponseEntity.ok(iUserService.findUserByIdService(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseBodyDTO> updateUser(@PathVariable UUID userId, @Valid @RequestBody UserRequestBodyDTO userRequestBodyDTO){
        return ResponseEntity.ok(iUserService.updateUserService(userId, userRequestBodyDTO));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId){
        iUserService.deleteUserService(userId);
        return ResponseEntity.noContent().build();
    }

}
