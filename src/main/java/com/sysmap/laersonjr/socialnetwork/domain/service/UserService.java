package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.dto.request.UserRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.dto.response.UserResponseBodyDTO;
import com.sysmap.laersonjr.socialnetwork.core.security.exception.ForbiddenActionException;
import com.sysmap.laersonjr.socialnetwork.domain.exception.UserNotFoundException;
import com.sysmap.laersonjr.socialnetwork.domain.entity.User;
import com.sysmap.laersonjr.socialnetwork.domain.repository.UserRepository;
import com.sysmap.laersonjr.socialnetwork.domain.service.validator.IUserValidator;
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

    @Autowired
    private IUserValidator iUserValidator;

    @Override
    public UserResponseBodyDTO createUserService(UserRequestBodyDTO userRequestBodyDTO) {
        User user = iModelMapperDTOConverter.convertToEntity(userRequestBodyDTO, User.class);
        iUserValidator.checkUserExistenceByEmailOrNickname(user.getNickName(), user.getEmail());
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


    //TODO: Acrescentar regra e DTO para atualizar apenas dados recebidos.
    @Override
    public UserResponseBodyDTO updateUserService(UUID userId, UserRequestBodyDTO userRequestBodyDTO, HttpServletRequest request) {
        User userFound = findUserService(userId);
        if (iUserValidator.isUserNotOwnership(request, userFound)) {
            throw new ForbiddenActionException();
        }
        iUserValidator.checkUserExistenceByEmailOrNicknameUpdate(userRequestBodyDTO.getNickName(), userRequestBodyDTO.getEmail(), userId);
        BeanUtils.copyProperties(userRequestBodyDTO, userFound, "id");
        userFound.setUpdatedDate();
        User updatedUser = userRepository.save(userFound);
        return iModelMapperDTOConverter.convertToModelDTO(updatedUser, UserResponseBodyDTO.class);
    }

    @Override
    public void deleteUserService(UUID userId, HttpServletRequest request) {
        User userFound = findUserService(userId);
        if (iUserValidator.isUserNotOwnership(request, userFound)) {
            throw new ForbiddenActionException();
        }
        userRepository.deleteById(userFound.getId());
    }


}
