package com.cadastroELoginEstudos.exception;

public class RecursoNaoEncontradoException extends ApiException {
    public RecursoNaoEncontradoException(String recurso) {
        super(recurso + " não encontrado");
    }

    @Override
    public int getStatus(){
      return 404;
    }
}
