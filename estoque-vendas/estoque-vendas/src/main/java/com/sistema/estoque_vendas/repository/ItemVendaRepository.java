package com.sistema.estoque_vendas.repository;

import com.sistema.estoque_vendas.model.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
}
