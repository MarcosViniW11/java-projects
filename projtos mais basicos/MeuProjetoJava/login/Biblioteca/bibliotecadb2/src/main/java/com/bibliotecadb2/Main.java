package com.bibliotecadb2;


import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //livroDAO dao = new livroDAO();
        //Livro livro1=new Livro("Chapeuzinho vermelho","Abuquerq",1992,"Comédia",20);
        //dao.CadastrarLivro(livro1);

        //List<Livro> livros = dao.ListarLivros();
        //for(Livro livro:livros){
          //  System.out.println(livro);
        //}
        //dao.AtualizarLivroPorId(livro1,3);
        //dao.ExcluirLivroPorId(2);

        //UsuarioDAO dao = new UsuarioDAO();
        //Usuario usuario = new Usuario("Lucas","Lucas@gmail.com","(26)33544899");
        //dao.CadastrarUsuario(usuario);

        //List<Usuario> usuarios = dao.ListarUsuarios();
        //for(Usuario u : usuarios){
         //   System.out.println(u);
        //}

        //dao.AtualizarUsuarioPorId(usuario,1);
        //dao.ExcluirUsuarioPorId(1);

            EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

            // Criando um empréstimo de teste
            Emprestimo emp = new Emprestimo();

            // ⚠️ Aqui você precisa usar IDs que existam no seu banco (Usuario e Livro já cadastrados)
            emp.setUsuarioId(3); // exemplo: ID 1 do usuário
            emp.setLivroId(3);   // exemplo: ID 1 do livro

            // Datas
            emp.setDataEmprestimo(new Date(System.currentTimeMillis()));
            emp.setDataDevolucao(new Date(System.currentTimeMillis() + (7L * 24 * 60 * 60 * 1000))); // 7 dias depois
            emp.setDevolvido(false);

            // Cadastrando
            emprestimoDAO.CadastrarEmprestimo(emp);

            // Listando todos para conferir
            for (Emprestimo e : emprestimoDAO.listarEmprestimos()) {
                System.out.println("ID: " + e.getId() +
                        " | UsuarioID: " + e.getUsuarioId() +
                        " | LivroID: " + e.getLivroId() +
                        " | Emprestimo: " + e.getDataEmprestimo() +
                        " | Devolução: " + e.getDataDevolucao() +
                        " | Devolvido: " + e.isDevolvido());
            }
        }



    }
