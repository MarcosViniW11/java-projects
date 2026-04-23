package com.retomada.CadastroELoginRetomadanow.dto;

import com.retomada.CadastroELoginRetomadanow.entity.Role;

public record UsuarioResponse(
        Long id,
        String email,
        Role role
) {
}
