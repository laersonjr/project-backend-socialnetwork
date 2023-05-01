package com.sysmap.laersonjr.socialnetwork.domain.exception;

public class UsuarioNotFoundException extends RuntimeException{

    private static final String ERROR_MESSAGE = "Usuário not found.";

    public UsuarioNotFoundException(){
        super((ERROR_MESSAGE));
    }

}
