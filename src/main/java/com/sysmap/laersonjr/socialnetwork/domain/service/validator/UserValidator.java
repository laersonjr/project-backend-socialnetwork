package com.sysmap.laersonjr.socialnetwork.domain.service.validator;


import com.sysmap.laersonjr.socialnetwork.domain.entity.User;
import com.sysmap.laersonjr.socialnetwork.domain.exception.EmailAlreadyExistsException;
import com.sysmap.laersonjr.socialnetwork.domain.exception.NicknameAlreadyExistsException;
import com.sysmap.laersonjr.socialnetwork.domain.repository.UserRepository;
import com.sysmap.laersonjr.socialnetwork.domain.service.IAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class UserValidator implements IUserValidator {

    @Autowired
    private IAuthenticationService iAuthenticationService;

    @Autowired
    private UserRepository userRepository;

    public boolean isUserNotOwnership(HttpServletRequest request, User userFound) {
        return !userFound.getId().equals(iAuthenticationService.getAuthenticatedUser(request).getId());
    }

    public boolean checkUserExistenceByEmailOrNickname(String nickName, String email) {
        if (userRepository.findByNickName(nickName) != null) {
            throw new NicknameAlreadyExistsException();
        }
        if (userRepository.findByEmail(email) != null) {
            throw new EmailAlreadyExistsException();
        }
        return true;
    }

    public boolean checkUserExistenceByEmailOrNicknameUpdate(String nickName, String email, UUID userId) {
        User userByNickName = userRepository.findByNickName(nickName);
        if (userByNickName != null && !userByNickName.getId().equals(userId)) {
            throw new NicknameAlreadyExistsException();
        }
        User userByEmail = userRepository.findByEmail(email);
        if (userByEmail != null && !userByEmail.getId().equals(userId)) {
            throw new EmailAlreadyExistsException();
        }
        return true;
    }

}
