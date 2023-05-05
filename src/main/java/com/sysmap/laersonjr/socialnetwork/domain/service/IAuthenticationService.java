package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.UserAuthenticationDTO;

public interface IAuthenticationService {

    String authenticateUser(UserAuthenticationDTO login);

}
