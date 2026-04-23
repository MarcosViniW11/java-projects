package com.retomada.CadastroELoginRetomadanow.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

public record Login(
        @Email String email,
        @Valid String senha
) {
}
