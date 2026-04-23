package br.com.barbearia.sistemaBarbearia.entity;

import br.com.barbearia.sistemaBarbearia.enums.StatusAgendamento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "agendamentos")
@Getter
@Setter
@NoArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Servico servico;

    private LocalDateTime dataHoraInicio;

    private LocalDateTime dataHoraFim;

    @Enumerated(EnumType.STRING)
    @JoinColumn(nullable = false)
    private StatusAgendamento status;

}
