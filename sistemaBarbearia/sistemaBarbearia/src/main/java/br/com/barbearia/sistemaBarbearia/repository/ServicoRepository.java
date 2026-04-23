package br.com.barbearia.sistemaBarbearia.repository;

import br.com.barbearia.sistemaBarbearia.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico , Long> {
}
