package com.sistema.loginEcadastro.exception;

public class EmailJaCadastradoException extends ApiException{

    public EmailJaCadastradoException(){
        super("Email já cadastrado");
    }

    @Override
    public int getStatus(){
        return 409;
    }

}
