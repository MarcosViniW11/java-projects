package com.loja;
import java.util.List;


public class Main {
    public static void main(String[] args) {
    ProdutoDAO dao = new ProdutoDAO();

    //Produto p1=new Produto("Teclado Mecânico", 299.90, 10);
    //Produto p2 = new Produto("Mouse Gamer", 129.50, 25);
    //Produto p=new Produto("notebook",3500.00,5);

    //dao.inserir(p);
    //dao.inserir(p2);


    System.out.println("PRODUTOS CADASTRADOS");
    List<Produto> produtos = dao.listarTodos();
    for (Produto produto : produtos) {
        System.out.println(produto);
    }

    }
}