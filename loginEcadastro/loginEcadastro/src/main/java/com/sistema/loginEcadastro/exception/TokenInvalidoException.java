package com.sistema.loginEcadastro.exception;

public class TokenInvalidoException extends ApiException{

    public TokenInvalidoException(){
        super("Token invalido ou expirado");
    }

    @Override
    public int getStatus(){
        return 401;
    }

}
