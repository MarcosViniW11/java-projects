package com.sistema.estoque_vendas.repository;

import com.sistema.estoque_vendas.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
}
