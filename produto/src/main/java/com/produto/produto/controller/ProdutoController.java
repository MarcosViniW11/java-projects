package com.produto.produto.controller;

import com.produto.produto.model.Produto;
import com.produto.produto.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")// ou pode ser o live sever origem como:@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    public final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> ListarTodosProdutos() {
        return produtoService.ListarProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable long id) {
        return produtoService.BuscarProdutoPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Produto> adicionar(@RequestBody Produto produto) {
        Produto produtoSalvar=produtoService.salvar(produto);
        return ResponseEntity.ok().body(produtoSalvar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable long id, @RequestBody Produto produto) {
        return produtoService.BuscarProdutoPorId(id).map(existente->{
            produto.setId(id);
            return ResponseEntity.ok().body(produtoService.salvar(produto));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerProduto(@PathVariable long id) {
        produtoService.removerProduto(id);
        return ResponseEntity.noContent().build();
    }

}
