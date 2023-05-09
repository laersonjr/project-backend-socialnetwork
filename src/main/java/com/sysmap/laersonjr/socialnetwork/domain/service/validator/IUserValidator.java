package com.sysmap.laersonjr.socialnetwork.domain.service.validator;

import com.sysmap.laersonjr.socialnetwork.domain.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public interface IUserValidator {

    boolean isUserNotOwnership(HttpServletRequest request, User userFound);
    boolean checkUserExistenceByEmailOrNickname(String nickName, String email);
    boolean checkUserExistenceByEmailOrNicknameUpdate(String nickName, String email, UUID userId);

    boolean checkFrindList(User requestUser, User friendUser);
}
