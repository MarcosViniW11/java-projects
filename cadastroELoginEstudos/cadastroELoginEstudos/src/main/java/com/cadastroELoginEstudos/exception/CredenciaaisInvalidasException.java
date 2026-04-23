package com.cadastroELoginEstudos.exception;

public class CredenciaaisInvalidasException extends ApiException {

    public CredenciaaisInvalidasException() {
        super("Credenciais Inválidas");
    }

    @Override
    public int getStatus(){
        return 401;
    }
}
