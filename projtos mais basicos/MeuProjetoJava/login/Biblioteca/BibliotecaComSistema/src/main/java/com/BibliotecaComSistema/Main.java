package com.BibliotecaComSistema;


import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        LivroDAO livroDAO = new LivroDAO();
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

        Scanner sc = new Scanner(System.in);
        int opcao=1;
        while(opcao!=0){
            System.out.println("BIBLIOTECA");
            System.out.println("ESCOLHA UMA OPÇÃO:");
            System.out.println("0 - Sair");
            System.out.println("1 - ACESSAR AS OPÇÕES PARA USUARIO");
            System.out.println("2 - ACESSAR AS OPÇÕES PARA LIVRO");
            System.out.println("3 - ACESSAR AS OPÇÕES PARA EMPRESTIMO");


            opcao=sc.nextInt();
            switch (opcao){
                case 1:
                    System.out.println("DIGITE QUAL A OPÇÃO DESEJA REALIZAR COM A TABELA DE USUARIOS");
                    System.out.println("0-para voltar ao menu Principal");
                    System.out.println("1-Para Cadastrar");
                    System.out.println("2-Para Listar");
                    System.out.println("3-Para Buscar");
                    System.out.println("4-Para Atualizar");
                    System.out.println("5-Para Remover");

                    int opcao2=sc.nextInt();
                    sc.nextLine();

                    while (opcao2!=0){
                        switch (opcao2){
                            case 1:
                                System.out.println("Digite o nome:");
                                String nome=sc.next();
                                System.out.println("Digite o email:");
                                String email=sc.next();
                                System.out.println("Digite o telefone:");
                                int telefone=sc.nextInt();
                                Usuario usuario=new Usuario(nome,email,telefone);
                                usuarioDAO.CadastrarUsuario(usuario);
                                opcao2=0;
                                break;

                            case 2:
                                List<Usuario> lista=usuarioDAO.ListarUsuarios();
                                for (Usuario u : lista){
                                    System.out.println(u);
                                }
                                opcao2=0;
                                break;

                            case 3:
                                System.out.println("Digite o ID PARA PROCURAR no SISTEMA:");
                                int id=sc.nextInt();
                                Usuario usuarioEncontrado=usuarioDAO.BuscarUsuarioPorId(id);
                                System.out.println(usuarioEncontrado);

                                break;

                           case 4:
                               System.out.println("Digite o id que deseja atualizar:");
                               int pId=sc.nextInt();
                               System.out.println("Digite o novo nome");
                               String Nome=sc.next();
                               System.out.println("Digite o novo email");
                               String Email=sc.next();
                               System.out.println("Digite o novo telefone");
                               int Telefone=sc.nextInt();
                               Usuario NovoUsuario=new Usuario(Nome,Email,Telefone);
                               usuarioDAO.AlterarUsuarioPorId(NovoUsuario,pId);
                               opcao2=0;
                               break;

                           case 5:
                               System.out.println("Digite o id para remover:");
                               int idP=sc.nextInt();
                               usuarioDAO.ExcluirUsuarioPorId(idP);
                               opcao2=0;


                               break;

                           case 0:
                                System.out.println("Voltando ao Menu Principal....");
                                break;

                           default:
                                System.out.println("DIGITE UMA OPÇÃO VALIDA!!");
                                opcao2=0;
                                break;
                        }

                    }
                    break;

                case 2:
                    System.out.println("OPÇÕES LIVROS");
                    System.out.println("0 - Sair PARA MENU");
                    System.out.println("1 - Para Cadastrar LIVRO");
                    System.out.println("2 - Para Listar LIVROS");
                    System.out.println("3 - Para Buscar LIVRO");
                    System.out.println("4 - Para Atualizar LIVRO");
                    System.out.println("5 - Para Remover LIVRO");
                    int opcao3=sc.nextInt();
                    sc.nextLine();

                    while (opcao3!=0){
                        switch (opcao3){
                            case 1:
                                System.out.println("Digite o Titulo:");
                                String titulo = sc.nextLine();

                                System.out.println("Digite o Autor:");
                                String autor = sc.nextLine();

                                System.out.println("Digite o Ano De Publicação:");
                                int anoPublicacao = sc.nextInt();

                                System.out.println("Digite a Quantidade:");
                                int quantidade = sc.nextInt();

                                Livro livro = new Livro(titulo, autor, anoPublicacao, quantidade);
                                livroDAO.CadastrarLivro(livro);
                                opcao3 = 0;


                                break;
                            case 2:
                                System.out.println("Lista de Livros");
                                List<Livro> lista=livroDAO.ListarLivros();
                                for (Livro l : lista){
                                    System.out.println(l);
                                }
                                opcao3=0;
                                break;

                            case 3:
                                System.out.println("digite o id do livro que deseja buscar");
                                int idLivro=sc.nextInt();
                                Livro livroBuscar=livroDAO.BuscarLivro(idLivro);
                                System.out.println("Livro encontrado: "+livroBuscar);
                                opcao3=0;
                                break;

                            case 4:
                                System.out.println("Digite o id do livro que deseja atualizar");
                                int idLivroP=sc.nextInt();
                                sc.nextLine();

                                System.out.println("Digite o novo titulo:");
                                String tituloP = sc.nextLine();

                                System.out.println("Digite o novo Autor:");
                                String autorP = sc.nextLine();

                                System.out.println("Digite o novo ano publicacao:");
                                int anoPublicacaoP = sc.nextInt();
                                sc.nextLine();

                                System.out.println("Digite a Quantidade:");
                                int quantidadeP = sc.nextInt();
                                sc.nextLine();

                                Livro livroEditado=new Livro(tituloP,autorP,anoPublicacaoP,quantidadeP);
                                livroDAO.atualizarLivro(livroEditado,idLivroP);
                                opcao3=0;

                                break;

                            case 5:

                                System.out.println("Digite o id para remover:");
                                int idP=sc.nextInt();
                                livroDAO.excluirLivro(idP);
                                opcao3=0;

                                break;
                        }
                    }



                break;

                case 3:
                    System.out.println("EMPRESTIMOS:");
                    System.out.println("0 - PARA voltar ao menu");
                    System.out.println("1 - CADASTRAR UM EMPRESTIMO");
                    System.out.println("2 - Para Listar EMPRESTIMOS");
                    System.out.println("3 - para Excluir EMPRESTIMO");
                    System.out.println("4 - para Listar EMPRESTIMOS FINALIZADOS");
                    int op3=sc.nextInt();
                    sc.nextLine();
                    while (op3!=0){
                        switch (op3){
                            case 1:
                                System.out.println("Digite o id do usuario");
                                int idUsuario=sc.nextInt();
                                sc.nextLine();

                                System.out.println("Digite o id do livro:");
                                int idLivro=sc.nextInt();

                                java.util.Date dataEmprestimo=new java.util.Date();
                                java.util.Date dataPrevista=new java.util.Date(System.currentTimeMillis()+ (7L * 24 * 60 * 60 * 1000));

                                Emprestimo emprestimo=new Emprestimo();
                                emprestimo.setUsuarioId(idUsuario);
                                emprestimo.setLivroId(idLivro);
                                emprestimo.setDataEmprestimo(dataEmprestimo);
                                emprestimo.setDataDevolucao(null);
                                emprestimo.setDataPrevista(dataPrevista);

                                boolean sucesso=emprestimoDAO.CadastrarEmprestimo(emprestimo);
                                if(sucesso){
                                    System.out.println("Emprestimo Cadastrado com sucesso!");
                                }else {
                                    System.out.println("Emprestimo não Cadastrado!");
                                }

                                op3=0;
                                break;

                            case 2:
                                List<EmprestimoDetalhe> emprestimos=emprestimoDAO.ListarEmprestimosComDetalhe();
                                for (EmprestimoDetalhe emp : emprestimos){
                                    System.out.println(emp);
                                }


                                op3=0;
                                break;

                            case 3:

                                System.out.println("Digite o id do Emprestimo que deseja remover para devolver o livro");
                                int idP=sc.nextInt();

                                boolean resultado=emprestimoDAO.ExcluirEmprestimo(idP);

                                if(resultado){
                                    System.out.println("Emprestimo removido com sucesso! e copiado ao historico");
                                }else{
                                    System.out.println("OCORREU UM ERRO");
                                }


                                op3=0;
                                break;

                            case 4:
                                System.out.println("Lista de Emprestimos Finalizados:");

                                List<emprestimoFinalizado> EmprestimosFinalizados=emprestimoDAO.ListarEmprestimosFinalizados();
                                for (emprestimoFinalizado e : EmprestimosFinalizados){
                                    System.out.println(e);
                                }

                                op3=0;
                                break;

                            case 0:
                                System.out.println("SAINDO PARA MENU....");
                                op3=0;
                                break;
                        }
                    }


                    break;

                case 0:
                    System.out.println("Fechando sistema...");
                    break;

                default:
                    System.out.println("DIGITE UMA OPÇÃO VALIDA");
            }

        }






    }
}