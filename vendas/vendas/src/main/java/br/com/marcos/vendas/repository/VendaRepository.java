package br.com.marcos.vendas.repository;

import br.com.marcos.vendas.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long>{
}
