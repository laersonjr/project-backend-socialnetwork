package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.UserRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UserResponseBodyDTO;
import com.sysmap.laersonjr.socialnetwork.domain.exception.ForbiddenActionException;
import com.sysmap.laersonjr.socialnetwork.domain.exception.UserNotFoundException;
import com.sysmap.laersonjr.socialnetwork.domain.model.User;
import com.sysmap.laersonjr.socialnetwork.domain.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IModelMapperDTOConverter iModelMapperDTOConverter;

    @Autowired
    private IAuthenticationService iAuthenticationService;

    @Override
    public UserResponseBodyDTO createUserService(UserRequestBodyDTO userRequestBodyDTO) {
        User user = iModelMapperDTOConverter.convertToEntity(userRequestBodyDTO, User.class);
        user.setCreatedDate();
        user.setId();
        userRepository.save(user);
        UserResponseBodyDTO createdUser = iModelMapperDTOConverter.convertToModelDTO(user, UserResponseBodyDTO.class);
        return createdUser;
    }

    @Override
    public Page<UserResponseBodyDTO> userListingService(String nickName, Pageable pageable, HttpServletRequest request) {
        iAuthenticationService.validadTokenByRequest(request);
        Page<User> usersList = userRepository.findByNickNameContains(nickName, pageable);
        return iModelMapperDTOConverter.convertToModelPageDTO(usersList, UserResponseBodyDTO.class);
    }

    @Override
    public UserResponseBodyDTO findUserByIdService(UUID userId, HttpServletRequest request) {
        iAuthenticationService.validadTokenByRequest(request);
        return iModelMapperDTOConverter.convertToModelDTO(findUserService(userId), UserResponseBodyDTO.class);
    }

    @Override
    public User findUserService(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
    }

    @Override
    public User findUserByEmailService(String email) {
        return userRepository.findByEmail(email);
    }

    //TODO: Acrescentar regra e DTO para atualizar apenas dados recebidos.
    @Override
    public UserResponseBodyDTO updateUserService(UUID userId, UserRequestBodyDTO userRequestBodyDTO, HttpServletRequest request) {
        User userFound = findUserService(userId);
        if(isUserNotOwnership(request, userFound)){
            throw new ForbiddenActionException();
        }
        BeanUtils.copyProperties(userRequestBodyDTO, userFound, "id");
        userFound.setUpdatedDate();
        User updatedUser = userRepository.save(userFound);
        return iModelMapperDTOConverter.convertToModelDTO(updatedUser, UserResponseBodyDTO.class);
    }

    private boolean isUserNotOwnership(HttpServletRequest request, User userFound) {
        return !userFound.getId().equals(iAuthenticationService.getAuthenticatedUser(request).getId());
    }

    @Override
    public void deleteUserService(UUID userId, HttpServletRequest request) {
        User userFound = findUserService(userId);
        if(isUserNotOwnership(request, userFound)){
            throw new ForbiddenActionException();
        }
        userRepository.deleteById(userFound.getId());
    }



}
