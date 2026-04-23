package br.com.barbearia.sistemaBarbearia.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriarClienteDTO {
    private String nome;
    private String telefone;
    private String email;
    private String senha;
}
