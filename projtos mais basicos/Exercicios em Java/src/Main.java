import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Agenda agenda = new Agenda();

        int opcao;
        do {
            System.out.println("\n📱 MENU AGENDA");
            System.out.println("1 - Adicionar contato");
            System.out.println("2 - Listar contatos");
            System.out.println("3 - Buscar contato");
            System.out.println("4 - Editar contato");
            System.out.println("5 - Remover contato");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            // Aqui pode dar erro se digitar letra → melhor tratar
            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Digite apenas números!");
                opcao = -1; // força repetir o menu
            }

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = sc.nextLine();
                    agenda.adicionarContato(nome, telefone);
                }
                case 2 -> agenda.listarContatos();
                case 3 -> {
                    System.out.print("Digite o nome para buscar: ");
                    String busca = sc.nextLine();
                    agenda.buscarContato(busca);
                }
                case 4 -> {
                    System.out.print("Nome do contato a editar: ");
                    String nomeAntigo = sc.nextLine();
                    System.out.print("Novo nome (ou deixe vazio): ");
                    String novoNome = sc.nextLine();
                    System.out.print("Novo telefone (ou deixe vazio): ");
                    String novoTelefone = sc.nextLine();
                    agenda.editarContato(nomeAntigo, novoNome, novoTelefone);
                }
                case 5 -> {
                    System.out.print("Nome do contato a remover: ");
                    String remover = sc.nextLine();
                    agenda.removerContato(remover);
                }
                case 0 -> System.out.println("👋 Saindo da Agenda...");
                default -> System.out.println("❌ Opção inválida!");
            }
        } while (opcao != 0);

        sc.close();
    }
}