package com.cadastroELoginEstudos.exception;

public class AcessoNegadoException extends ApiException {
    public AcessoNegadoException() {
        super("Acesso Negado");
    }

    @Override
    public int getStatus(){ return 403; }
}
