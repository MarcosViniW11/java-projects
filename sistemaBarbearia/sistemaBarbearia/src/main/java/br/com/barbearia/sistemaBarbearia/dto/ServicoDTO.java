package br.com.barbearia.sistemaBarbearia.dto;

public record ServicoDTO(
        String nome,
        Integer duracaoMinutos,
        Double preco
) {
}
