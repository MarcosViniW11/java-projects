package com.Blioteca;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BibliotecaDAO dao = new BibliotecaDAO();

        Livro livro2 = new Livro("Maria e joao","ribeiro");

        dao.CadastrarLivro(livro2);

        dao.ListarLivros();

        //List<Livro> livros = new ArrayList<>();
        //livros=dao.ListarLivros();
        //for(Livro livro:livros){
          //  System.out.println("Livro: "+livro);
        //}

        //dao.DeletarLivro(7);
       // Livro livro=dao.BuscarLivro(1);
        //System.out.println(livro);

        //dao.DeletarLivro(1);

    }
}