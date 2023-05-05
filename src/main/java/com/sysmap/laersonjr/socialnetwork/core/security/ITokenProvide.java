package com.sysmap.laersonjr.socialnetwork.core.security;

public interface ITokenProvide {

    String generateToken(String email);
    boolean validateToken(String token);
    String getEmailFromToken(String token);
}
