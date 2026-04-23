package br.com.marcos.vendas.repository;

import br.com.marcos.vendas.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
}
