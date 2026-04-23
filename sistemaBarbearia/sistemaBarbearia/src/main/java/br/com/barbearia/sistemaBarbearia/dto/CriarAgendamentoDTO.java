package br.com.barbearia.sistemaBarbearia.dto;

import java.time.LocalDateTime;

public record CriarAgendamentoDTO(
        Long clienteId,
        Long profissionalId,
        Long servicoId,
        LocalDateTime dataHoraInicio
) {
}
