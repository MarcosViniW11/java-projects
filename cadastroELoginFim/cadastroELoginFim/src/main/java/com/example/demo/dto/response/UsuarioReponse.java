package com.example.demo.dto.response;

import com.example.demo.entity.Role;

public record UsuarioReponse(
        Long id,
        String email,
        Role role
) {
}
