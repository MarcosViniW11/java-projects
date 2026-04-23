package com.sistema.estoque_vendas.repository;

import com.sistema.estoque_vendas.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
