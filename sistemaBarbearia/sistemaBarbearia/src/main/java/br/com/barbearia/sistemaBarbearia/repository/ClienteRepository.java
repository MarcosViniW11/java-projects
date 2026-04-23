package br.com.barbearia.sistemaBarbearia.repository;

import br.com.barbearia.sistemaBarbearia.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente , Long> {

}
