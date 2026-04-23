package br.com.marcos.vendas.controller;

import br.com.marcos.vendas.model.Produto;
import br.com.marcos.vendas.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    public final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> listar(){
        return produtoService.listarProdutos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable Long id){
        return produtoService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Produto> adicionar(@RequestBody Produto produtoSalvar){
        Produto produto=produtoService.salvar(produtoSalvar);
        return ResponseEntity.ok().body(produto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable long id, @RequestBody Produto produto){
        return produtoService.buscarPorId(id).map(existente->{produto.setId(id);return ResponseEntity.ok().body(produtoService.salvar(produto));}).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        produtoService.remover(id);
        return ResponseEntity.noContent().build();
    }


}
