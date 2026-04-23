package com.ProjetoBibliotecaFinal;


import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        LivroDAO livroDAO = new LivroDAO();
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
        Scanner sc = new Scanner(System.in);
        Boolean opcao=true;

        while(opcao){
            System.out.println("MENU BIBLIOTECA");
            System.out.println("1. PARA OPÇÕES DE USUARIO");
            System.out.println("2. PARA OPÇÕES DE LIVRO");
            System.out.println("3. PARA OPÇÕES DE EMPRESTIMO");
            System.out.println("0. Sair");
            int opcaoInicial = sc.nextInt();
            sc.nextLine();

            switch (opcaoInicial){
                case 1:
                    System.out.println("Opções para USUARIO:");
                    System.out.println("1. Cadastrar");
                    System.out.println("2. Listar");
                    System.out.println("3. atualizar");
                    System.out.println("4. excluir");
                    System.out.println("5. Atualizar quantidade de emprestimos ativos");
                    System.out.println("6. listar Usuarios com emprestimos ativos");
                    int opcaoInicial2 = sc.nextInt();
                    sc.nextLine();
                    switch (opcaoInicial2){
                        case 1:
                            System.out.println("Digite o nome do usuario:");
                            String nome = sc.nextLine();
                            System.out.println("Digite o email do usuario:");
                            String email = sc.nextLine();
                            System.out.println("Digite o telefone do usuario:");
                            String telefone = sc.nextLine();
                            System.out.println("Escolha o tipo de Usuario,1-Aluno ou 2-Professor ou 3-Outro");
                            int tipoUsuario = sc.nextInt();
                            String tipoUsuarioFinal="Outro";
                            int LimiteDeEmprestimo=3;
                            sc.nextLine();
                            switch (tipoUsuario){
                                case 1:
                                    tipoUsuarioFinal="Aluno";
                                    LimiteDeEmprestimo = 3;
                                    break;
                                case 2:
                                    tipoUsuarioFinal="Professor";
                                    LimiteDeEmprestimo = 5;
                                    break;
                                case 3:
                                    tipoUsuarioFinal="Outro";
                                    LimiteDeEmprestimo = 3;
                                    break;
                                default:
                                    System.out.println("Digite uma opcao valida!");
                            }
                            int qtbEmprestimosAtivos = 0;
                            System.out.println("Digite o Status do Usuario:(Ativo) ou (Inativo)");
                            String status = sc.nextLine();
                            Usuario usuario=new Usuario(nome,email,telefone,tipoUsuarioFinal,qtbEmprestimosAtivos,LimiteDeEmprestimo,status);
                            Boolean sucesso=usuarioDAO.CadastrarUsuario(usuario);
                            if(sucesso){
                                System.out.println("Usuario cadastrado com sucesso!");
                            }else {
                                System.out.println("ouve um erro ao cadastrar usuario!");
                            }

                            break;
                        case 2:
                            List<Usuario> Usuarios=usuarioDAO.listarUsuarios();
                            for(Usuario listaUsuario:Usuarios){
                                System.out.println(listaUsuario);
                            }
                            break;
                        case 3:
                            System.out.println("Digite o id do usuario que deseja atualizar:");
                            int idUsuario = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Digite o novo nome do usuario:");
                            String nomeUsuario = sc.nextLine();
                            System.out.println("Digite o novo email do usuario:");
                            String emailUsuario = sc.nextLine();
                            System.out.println("Digite o novo telefone do usuario:");
                            String telefoneUsuario = sc.nextLine();
                            System.out.println("Digite o novo tipo de usuario (Aluno ou Professor ou Outro)");
                            String tipoUsuarioUsuario = sc.nextLine();
                            int novoQtbEmprestimosAtivos= 0 ;
                            int NovoLimiteDeEmprestimo= 0;
                            if(tipoUsuarioUsuario.equals("Aluno") || tipoUsuarioUsuario.equals("Outro")){
                                NovoLimiteDeEmprestimo=3;
                            }else if(tipoUsuarioUsuario.equals("Professor")){
                                NovoLimiteDeEmprestimo=5;
                            }
                            System.out.println("Digite o status do usuario que deseja atualizar:(Ativo) ou (Inativo)");
                            String novoStatusUsuario = sc.nextLine();
                            Usuario NovoUsuario=new Usuario(nomeUsuario,emailUsuario,telefoneUsuario,tipoUsuarioUsuario,novoQtbEmprestimosAtivos,NovoLimiteDeEmprestimo,novoStatusUsuario);
                            usuarioDAO.AtualizarUsuario(NovoUsuario,idUsuario);
                            break;

                        case 4:
                            System.out.println("Digite o id do usuario que deseja remover:");
                            int idRemover=sc.nextInt();
                            sc.nextLine();
                            usuarioDAO.ExcluirUsuario(idRemover);
                            break;
                        case 5:
                            System.out.println("Digite o id do usuario que deseja atualizar:");
                            int idAtualizarQTB=sc.nextInt();
                            sc.nextLine();
                            System.out.println("Digite a nova quantidade de emprestimos ativos para este usuario:");
                            int NovaQTB=sc.nextInt();
                            sc.nextLine();
                            usuarioDAO.atualizarQuantidadeEmprestimos(idAtualizarQTB,NovaQTB);
                            break;
                        case 6:
                            List<Usuario> ListaDeUsuariosAtivos= usuarioDAO.listarUsuariosComEmprestimoAtivo();
                            for(Usuario usuarioAtivos:ListaDeUsuariosAtivos){
                                System.out.println(usuarioAtivos);
                            }
                            break;


                    }
                case 2:
                    System.out.println("OPÇÕES PARA LIVRO");
                    System.out.println("1. Para cadastrar livro");
                    System.out.println("2. Para Listar livro");
                    System.out.println("3. Para atualizar livro");
                    System.out.println("4. Para remover livro");
                    System.out.println("5. Para atualizar o status do livro");
                    System.out.println("6. Para listar Livros Disponiveis");
                    int opcaocase2 = sc.nextInt();
                    sc.nextLine();
                    switch (opcaocase2) {
                        case 1:
                            System.out.println("Digite o titulo do livro:");
                            String tituloLivro = sc.nextLine();
                            System.out.println("Digite o autor do livro:");
                            String autorLivro = sc.nextLine();
                            System.out.println("Digite a categoria do livro:");
                            String categoriaLivro = sc.nextLine();
                            String statusLivro = "Disponivel";
                            Livro livro=new Livro(tituloLivro,autorLivro,categoriaLivro,statusLivro);
                            livroDAO.CadastrarLivro(livro);

                            break;
                        case 2:
                            List<Livro> ListaLivro=livroDAO.ListarLivro();
                            for(Livro livros:ListaLivro){
                                System.out.println(livros);
                            }
                            break;
                        case 3:
                            System.out.println("Digite o id do livro que deseja atualizar:");
                            int idLivro = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Digite o novo titulo do livro:");
                            String novoTituloLivro = sc.nextLine();
                            System.out.println("Digite o novo autor do livro:");
                            String novoAutorLivro = sc.nextLine();
                            System.out.println("Digite a nova categoria do livro:");
                            String novoCategoriaLivro = sc.nextLine();
                            System.out.println("Digite o novo status do livro:(Disponivel,Emprestado ou Reservado)");
                            String novoStatusLivro = sc.nextLine();
                            Livro novolivro=new Livro(novoTituloLivro,novoAutorLivro,novoCategoriaLivro,novoStatusLivro);
                            livroDAO.atualizarLivro(novolivro,idLivro);
                            break;
                        case 4:
                            System.out.println("Digite o id do livro que deseja remover:");
                            int idRemoverLivro = sc.nextInt();
                            sc.nextLine();
                            livroDAO.excluirLivro(idRemoverLivro);
                            break;
                        case 5:
                            System.out.println("Digite o id do livro que deseja atualizar o status:");
                            int idStatusLivro = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Digite o novo status do livro:(Disponivel ou Emprestado ou Reservado)");
                            String novoStatus=sc.nextLine();
                            livroDAO.AtualizarStatusLivro(idStatusLivro,novoStatus);
                            break;
                        case 6:
                            List<Livro> LivrosAtivos=livroDAO.ListarLivroDisponiveis();
                            for(Livro livros:LivrosAtivos){
                                System.out.println(livros);
                            }
                            break;
                        default:
                            System.out.println("Escolha uma opção validada");

                    }
                    break;
                case 3:
                    System.out.println("OPÇÕES PARA EMPRESTIMO");
                    System.out.println("1. Para cadastrar Emprestimo");
                    System.out.println("2. Para Listar Emprestimos");
                    System.out.println("3. Para Listar Emprestimos por usuario");
                    System.out.println("4. Para Resgistrar devolução");
                    System.out.println("5. Para Atualizar status de emprestimos atrasados");
                    System.out.println("6. Para Listar emprestimos ativos");
                    System.out.println("7. Para Listar emprestimos Ativo(s) por usuario");
                    System.out.println("8. Para verificar se o usuario pode ou não fazer emprestimo");
                    int opcaoCase3 = sc.nextInt();
                    sc.nextLine();
                    switch (opcaoCase3) {
                        case 1:
                            System.out.println("Digite o id do usuario para cadastrar o Emprestimo");
                            int idUsuario = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Digite o id do livro para cadastrar o Emprestimo");
                            int idLivro = sc.nextInt();
                            sc.nextLine();
                            Date dataEmprestimo = new Date(System.currentTimeMillis());
                            Date dataDevolucao = new Date();
                            System.out.println("Digite a duração desse emprestimo: 1 (uma semana), 2 (um mês) ou 3 (um ano)");
                            int duracaoEmprestimo = sc.nextInt();
                            sc.nextLine();
                            switch (duracaoEmprestimo) {
                                case 1:
                                    dataDevolucao=new Date(System.currentTimeMillis()+(7L * 24 * 60 * 60 * 1000));
                                    break;
                                case 2:
                                    dataDevolucao=new Date(System.currentTimeMillis()+(30L * 24 * 60 * 60 * 1000));
                                    break;
                                case 3:
                                    dataDevolucao=new Date(System.currentTimeMillis()+(365L * 24 * 60 * 60 * 1000));
                                    break;
                            }
                            String status="Ativo";
                            Emprestimo emprestimo=new Emprestimo(idUsuario,idLivro,dataEmprestimo,dataDevolucao,status);
                            Boolean sucesso=emprestimoDAO.CadastrarEmprestimo(emprestimo);
                            if(sucesso==true){
                                System.out.println("Emprestimo Cadastrado com sucesso");
                            }else {
                                System.out.println("Não foi possivel cadastrar Emprestimo");
                            }
                            break;
                        case 2:
                            List<Emprestimo> ListaEmprestimos=emprestimoDAO.ListarEmprestimos();
                            for(Emprestimo emprestimos:ListaEmprestimos){
                                System.out.println(emprestimos);
                            }
                            break;
                        case 3:
                            System.out.println("Digite o id do usuario para Listar Emprestimos");
                            int id = sc.nextInt();
                            sc.nextLine();
                            List<Emprestimo> emprestimosPorUsuario=emprestimoDAO.ListarEmprestimosPorUsuario(id);
                            for(Emprestimo emprestimos:emprestimosPorUsuario){
                                System.out.println(emprestimos);
                            }
                            break;
                        case 4:
                            System.out.println("Digite o id do emprestimo para registrar a devolução");
                            int idDevolucao = sc.nextInt();
                            sc.nextLine();
                            Boolean sucessoDevolucao=emprestimoDAO.RegistrarDevolucao(idDevolucao);
                            if(sucessoDevolucao){
                                System.out.println("devolução registrada com sucesso");
                            }else {
                                System.out.println("Ocorreu um erro ao registrar a devolucao");
                            }
                            break;
                        case 5:
                            Boolean sucessoEmprestimosAtrasados=emprestimoDAO.AtualizarStatusEmprestimoAtrasado();
                            if(sucessoEmprestimosAtrasados){
                                System.out.println("Emprestimo(s) atualizado com sucesso");
                            }else {
                                System.out.println("Ocorreu um erro ao atualizar o status do(s) emprestimos atrasados");
                            }
                            break;
                        case 6:
                            List<Emprestimo> ListaDeEmprestimosAtivos=emprestimoDAO.ListarEmprestimosAtivos();
                            for(Emprestimo emprestimos:ListaDeEmprestimosAtivos){
                                System.out.println(emprestimos);
                            }
                            break;
                        case 7:
                            System.out.println("Digite o id do usuario para Listar Emprestimos Ativos");
                            int idEmprestimoAtivo = sc.nextInt();
                            sc.nextLine();
                            List<Emprestimo> ListaEmprestimosAtivoPorUsuario=emprestimoDAO.ListarEmprestimosAtivosPorUsuario(idEmprestimoAtivo);
                            for(Emprestimo e:ListaEmprestimosAtivoPorUsuario){
                                System.out.println(e);
                            }
                            break;
                        case 8:
                            System.out.println("Digite o id do usuario para Verificar se Pode ou não fazer emprestimos");
                            int idPodeFazerEmprestimo = sc.nextInt();
                            sc.nextLine();
                            Boolean sucessoPodeOuNao=emprestimoDAO.PodeEmprestar(idPodeFazerEmprestimo);
                            if(sucessoPodeOuNao==false){
                                System.out.println("Este usuario não pode fazer emprestimos,pois ja bateu seu limite");
                            }else {
                                System.out.println("Este usuario pode fazer emprestimo(s)");
                            }

                            break;
                        default:
                            System.out.println("Digite um opcao valida");

                    }




                    break;

                case 0:
                    System.out.println("FECHANDO SISTEMA...ATÉ BREVE...");
                    opcao = false;
                    break;
                default:
                    System.out.println("Digite uma opção valida");
            }




        }











    }
}