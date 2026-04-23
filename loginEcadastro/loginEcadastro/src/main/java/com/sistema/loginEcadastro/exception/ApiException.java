package com.sistema.loginEcadastro.exception;

public abstract class ApiException extends RuntimeException {

    protected ApiException(String menssage){
        super(menssage);
    }

    public abstract int getStatus();

}
