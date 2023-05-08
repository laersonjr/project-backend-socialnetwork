package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.UserRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UserResponseBodyDTO;
import com.sysmap.laersonjr.socialnetwork.domain.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IUserService {

    UserResponseBodyDTO createUserService(UserRequestBodyDTO userRequestBodyDTO);
    Page<UserResponseBodyDTO> userListingService(String apelido, Pageable pageable, HttpServletRequest request);
    UserResponseBodyDTO findUserByIdService(UUID idUsuario, HttpServletRequest request);
    User findUserService(UUID idUsuario);
    UserResponseBodyDTO updateUserService(UUID idUsuario, UserRequestBodyDTO userRequestBodyDTO, HttpServletRequest request);
    void deleteUserService(UUID idUsuario, HttpServletRequest request);
    User findUserByEmailService(String email);
}
