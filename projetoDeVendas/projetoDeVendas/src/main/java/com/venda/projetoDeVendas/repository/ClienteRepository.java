package com.venda.projetoDeVendas.repository;

import com.venda.projetoDeVendas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
