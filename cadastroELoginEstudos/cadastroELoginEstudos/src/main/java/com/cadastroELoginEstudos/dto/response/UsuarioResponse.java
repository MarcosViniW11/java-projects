package com.cadastroELoginEstudos.dto.response;

import com.cadastroELoginEstudos.entity.Role;

public record UsuarioResponse (
        Long id,
        String email,
        Role role
){
}
