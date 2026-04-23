package com.cadastroELoginEstudos.exception;

public class TokenInvalidoException extends ApiException {
    public TokenInvalidoException() {
        super("Token Invalido ou expirado");
    }

    public int getStatus(){
        return 401;
    }
}
