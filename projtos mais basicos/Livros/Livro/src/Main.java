import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== BIBLIOTECA VIRTUAL =====");
            System.out.println("1 - Adicionar Livro");
            System.out.println("2 - Listar Livros");
            System.out.println("3 - Buscar Livro");
            System.out.println("4 - Remover Livro");
            System.out.println("5 - Editar Livro");
            System.out.println("6 - Mostrar Estatisticas da Lista");
            //System.out.println("7 - Emprestar Livro");
            //System.out.println("8 - Devolver Livro");
            System.out.println("9 - Listar Disponiveis");
            System.out.println("10 - Listar Indisponiveis");
            System.out.println("11 - Registrar Emprestimo");
            System.out.println("12 - Devolver Livro");
            System.out.println("13 - Listar Emprestimos");
            System.out.println("0 - Sair");
            System.out.println("==============================");
            System.out.print("Escolha uma opção: ");

            String opcao = sc.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("\nDigite o título do livro: ");
                    String titulo = sc.nextLine();

                    System.out.print("Digite o autor do livro: ");
                    String autor = sc.nextLine();

                    System.out.print("Digite o ISBN do livro: ");
                    String isbn = sc.nextLine();

                    biblioteca.adicionarLivro(titulo, autor, isbn);
                    break;

                case "2":
                    System.out.println("Deseja Listar de que forma?");
                    System.out.println("1 - Listar Livros de forma Padrão");
                    System.out.println("2 - Listar por Titulo do livro");
                    System.out.println("3 - Listar por Autor do livro");
                    String opn=sc.nextLine();
                    if(opn.equals("1")){
                        biblioteca.ListarLivros();
                    }if(opn.equals("2")){
                        biblioteca.ListarLivrosOrdenadoPorTitulo();
                    }if (opn.equals("3")){
                        biblioteca.ListarLivrosOrdenadoPorAutor();
                    }

                    break;

                case "3":
                    System.out.print("\nDigite o título do livro para buscar: ");
                    String busca = sc.nextLine();
                    biblioteca.BuscarLivro(busca);
                    break;

                case "4":
                    System.out.print("\nDigite o ISBN do livro para remover: ");
                    String isbnRemover = sc.nextLine();
                    biblioteca.RemoverLivro(isbnRemover);
                    break;
                case "5":
                    System.out.println("Digite o ISBN do livro para editar: ");
                    String isbnEditar = sc.nextLine();
                    biblioteca.editarLivro(isbnEditar);
                    break;

                case "6":
                    biblioteca.MostrarEstatistica();

                    break;

                /*case "7":
                    System.out.println("Digite o isbn do livro que deseja Emprestar");
                    String emprestar = sc.nextLine();
                    biblioteca.emprestarLivro(emprestar);
                    break;
                    */
                case "8":
                    System.out.println("Digite o isbn do livro que deseja Devolver");
                    String devolver = sc.nextLine();
                    biblioteca.devolverLivro(devolver);
                    break;

                case "9":
                    biblioteca.listarDisponiveis();
                    break;

                case "10":
                    biblioteca.listarIndisponiveis();
                    break;

                case "11":
                    System.out.println("Emprestimo de Livro:");
                    System.out.println("Digite o isbn do livro para o emprestimo: ");
                    String idemprestimo = sc.nextLine();
                    System.out.println("Digite o nome de usuario");
                    String nome = sc.nextLine();
                    biblioteca.registrarEmprestimo(idemprestimo, nome);

                    break;

                case "12":
                    System.out.println("Devolução de Livro:");
                    System.out.println("Digite o isbn do livro para a Devolução: ");
                    String devoluacao = sc.nextLine();
                    System.out.println("Digite o nome de usuario");
                    String nome2 = sc.nextLine();
                    biblioteca.devolverLivro(devoluacao, nome2);
                    break;
                case "13":
                    System.out.println("Listagem de Emprestimos:");
                    biblioteca.ListarEmprestimos();
                    break;
                case "0":
                    System.out.println("\nEncerrando o programa... Até logo!");
                    sc.close();
                    return;

                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
            }
        }
    }
}
