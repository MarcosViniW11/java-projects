package com.retomada.CadastroELoginRetomadanow.dto;

import com.retomada.CadastroELoginRetomadanow.entity.Role;

public record CadastroResponse (
        String email,
        Role role
){
}
