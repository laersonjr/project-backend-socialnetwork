package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.dto.request.UserAuthenticationDTO;
import com.sysmap.laersonjr.socialnetwork.domain.entity.User;
import jakarta.servlet.http.HttpServletRequest;

public interface IAuthenticationService {

    String authenticateUser(UserAuthenticationDTO login);

    User getAuthenticatedUser(HttpServletRequest request);

    boolean validadTokenByRequest(HttpServletRequest request);

}
