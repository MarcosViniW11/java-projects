package br.com.barbearia.sistemaBarbearia.repository;

import br.com.barbearia.sistemaBarbearia.entity.Agendamento;
import br.com.barbearia.sistemaBarbearia.entity.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface AgendamentoRepository extends JpaRepository<Agendamento , Long > {

    @Query("""
        SELECT COUNT(a) > 0 FROM Agendamento a
        WHERE a.profissional = :profissional
        AND :inicio < a.dataHoraFim
        AND :fim > a.dataHoraInicio
    """)
    boolean existeConflito(Profissional profissional,LocalDateTime inicio,LocalDateTime fim);


}
