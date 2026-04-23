package br.com.marcos.vendas.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ItemVenda> itemVenda;

    @Column(nullable = false)
    private LocalDateTime dataVenda;

    private double valorTotal;//deixei ele igual o passado,o usuario não prescisa colocar pois sera colocado automaticamente

    @Enumerated(EnumType.STRING)
    private VendaStatus ativo;



}
