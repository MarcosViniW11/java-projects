package com.venda.projetoDeVendas.repository;

import com.venda.projetoDeVendas.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
