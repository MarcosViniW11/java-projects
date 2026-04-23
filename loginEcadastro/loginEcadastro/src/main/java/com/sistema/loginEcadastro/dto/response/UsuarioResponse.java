package com.sistema.loginEcadastro.dto.response;

import com.sistema.loginEcadastro.entity.Role;

public record UsuarioResponse(
        Long id,
        String email,
        Role role
) {
}
