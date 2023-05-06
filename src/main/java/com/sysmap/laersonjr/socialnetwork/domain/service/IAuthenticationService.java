package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.UserAuthenticationDTO;
import com.sysmap.laersonjr.socialnetwork.domain.model.User;
import jakarta.servlet.http.HttpServletRequest;

public interface IAuthenticationService {

    String authenticateUser(UserAuthenticationDTO login);
    User getAuthenticatedUser(HttpServletRequest request);

}
