package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.UserAuthenticationDTO;
import com.sysmap.laersonjr.socialnetwork.core.security.ITokenProvide;
import com.sysmap.laersonjr.socialnetwork.domain.exception.IncorrectPasswordException;
import com.sysmap.laersonjr.socialnetwork.domain.exception.TokenNotFoundException;
import com.sysmap.laersonjr.socialnetwork.domain.exception.UserNotFoundException;
import com.sysmap.laersonjr.socialnetwork.domain.model.User;
import com.sysmap.laersonjr.socialnetwork.domain.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private ITokenProvide iTokenProvide;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String authenticateUser(UserAuthenticationDTO login) {

        User userFound = userRepository.findByEmail(login.getEmail());

        if (validateUserExists(userFound)) {
            throw new UserNotFoundException();
        }

        if (validadePassword(userFound, login)) {
            throw new IncorrectPasswordException();
        }

        String token = iTokenProvide.generateToken(login.getEmail());

        return token;

    }

    public User getAuthenticatedUser(HttpServletRequest request) {
        String token = getTokenByRequest(request);
        String email = iTokenProvide.getEmailFromToken(token);
        return userRepository.findByEmail(email);
    }

    private String getTokenByRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            throw new TokenNotFoundException();
        }
        String token = header.split(" ")[1];
        iTokenProvide.validateToken(token);
        return token;
    }

    public boolean validadTokenByRequest(HttpServletRequest request){
        return iTokenProvide.validateToken(getTokenByRequest(request));
    }


    private boolean validadePassword(User userFound, UserAuthenticationDTO login) {
        return !userFound.getPassword().equals(login.getPassword());
    }

    private boolean validateUserExists(User userFound) {
        return userFound == null;
    }


}
