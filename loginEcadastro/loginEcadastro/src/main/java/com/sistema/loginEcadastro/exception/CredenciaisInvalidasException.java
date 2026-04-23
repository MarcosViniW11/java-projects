package com.sistema.loginEcadastro.exception;

public class CredenciaisInvalidasException extends ApiException{

    public CredenciaisInvalidasException(){
        super("Credenciais Inválidas");
    }

    @Override
    public int getStatus(){
        return 401;
    }

}
