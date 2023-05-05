package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.UserAuthenticationDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UserAuthenticatedDTO;
import com.sysmap.laersonjr.socialnetwork.core.security.ITokenProvide;
import com.sysmap.laersonjr.socialnetwork.domain.exception.IncorrectPasswordException;
import com.sysmap.laersonjr.socialnetwork.domain.exception.UserNotFoundException;
import com.sysmap.laersonjr.socialnetwork.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ITokenProvide iTokenProvide;

    @Override
    public String authenticateUser(UserAuthenticationDTO login) {

        User userFound = iUserService.findUserByEmailService(login.getEmail());

        if (validateUserExists(userFound)) {
            throw new UserNotFoundException();
        }

        if (validadePassword(userFound, login)) {
            throw new IncorrectPasswordException();
        }

        String token = iTokenProvide.generateToken(login.getEmail());

        return token;

    }

    private boolean validadePassword(User userFound, UserAuthenticationDTO login) {
        return !userFound.getPassword().equals(login.getPassword());
    }

    private boolean validateUserExists(User userFound) {
        return userFound == null;
    }


}
