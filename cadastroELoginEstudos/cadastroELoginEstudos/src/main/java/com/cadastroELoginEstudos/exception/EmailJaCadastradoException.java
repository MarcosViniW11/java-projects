package com.cadastroELoginEstudos.exception;

public class EmailJaCadastradoException extends ApiException {

    public EmailJaCadastradoException() {
        super("Email já Cadastrado");
    }

    @Override
    public int getStatus(){
        return 409;
    }
}
