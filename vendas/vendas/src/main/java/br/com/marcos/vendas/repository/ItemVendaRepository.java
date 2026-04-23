package br.com.marcos.vendas.repository;

import br.com.marcos.vendas.model.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long>{
}
