package com.sistema.estoque_vendas.repository;

import com.sistema.estoque_vendas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
}
