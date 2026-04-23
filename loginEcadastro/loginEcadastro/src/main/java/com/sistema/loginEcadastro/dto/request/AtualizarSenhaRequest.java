package com.sistema.loginEcadastro.dto.request;


import jakarta.validation.constraints.NotBlank;

public record AtualizarSenhaRequest(
        @NotBlank String senhaAtual,
        @NotBlank String novaSenha
) {}
