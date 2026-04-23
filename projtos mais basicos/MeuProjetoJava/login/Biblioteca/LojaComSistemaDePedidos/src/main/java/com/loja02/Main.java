package com.loja02;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClientesDAO clientesDAO = new ClientesDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        PedidoDAO pedidoDAO = new PedidoDAO();

        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 9) {
            System.out.println("\n===== SISTEMA LOJA =====");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Cadastrar Produto");
            System.out.println("3 - Listar Clientes");
            System.out.println("4 - Listar Produtos");
            System.out.println("5 - Cadastrar Pedido");
            System.out.println("6 - Listar Pedidos");
            System.out.println("7 - Excluir Pedido");
            System.out.println("8 - Atualizar Quantidade de Pedido");
            System.out.println("9 - Sair");
            System.out.println("10 - Listar Pedidos Detalhados");

            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do cliente: ");
                    String nome = sc.next();
                    System.out.println("Digite o email do cliente: ");
                    String email = sc.next();
                    System.out.println("Digite o telefone do cliente: ");
                    String telefone = sc.next();

                    Clientes cliente = new Clientes(nome, email, telefone);
                    clientesDAO.CadastrarCliente(cliente);
                    System.out.println("✅ Cliente cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("Digite o nome do produto: ");
                    String nomeProduto = sc.next();
                    System.out.println("Digite o preço do produto: ");
                    int precoProduto = sc.nextInt();
                    System.out.println("Digite a quantidade em estoque: ");
                    int quantidadeProduto = sc.nextInt();

                    Produto produto = new Produto(nomeProduto, precoProduto, quantidadeProduto);
                    produtoDAO.CadastrarProduto(produto);
                    System.out.println("✅ Produto cadastrado com sucesso!");
                    break;

                case 3:
                    System.out.println("\n--- LISTA DE CLIENTES ---");
                    List<Clientes> clientes = clientesDAO.listarClientes();
                    for (Clientes c : clientes) {
                        System.out.println(c);
                    }
                    break;

                case 4:
                    System.out.println("\n--- LISTA DE PRODUTOS ---");
                    List<Produto> listaProduto = produtoDAO.ListarProdutos();
                    for (Produto p : listaProduto) {
                        System.out.println(p);
                    }
                    break;

                case 5:
                    System.out.println("Digite o ID do cliente: ");
                    int idCliente = sc.nextInt();
                    Clientes clientePedido = clientesDAO.buscarClientePorId(idCliente);

                    if (clientePedido == null) {
                        System.out.println("⚠ Cliente não encontrado!");
                        break;
                    }

                    System.out.println("Digite o ID do produto: ");
                    int idProduto = sc.nextInt();
                    Produto produtoPedido = produtoDAO.BuscarProdutoPorId(idProduto);

                    if (produtoPedido == null) {
                        System.out.println("⚠ Produto não encontrado!");
                        break;
                    }

                    System.out.println("Digite a quantidade: ");
                    int quantidade = sc.nextInt();

                    if (quantidade > produtoPedido.getQuantidade()) {
                        System.out.println("⚠ Estoque insuficiente! Só há " + produtoPedido.getQuantidade() + " unidades.");
                        break;
                    }

                    Date data = new Date(System.currentTimeMillis());
                    Pedido pedido = new Pedido(idCliente, idProduto, quantidade, data);

                    pedidoDAO.CadastrarPedido(pedido);

                    // Atualiza o estoque
                    produtoPedido.setQuantidade(produtoPedido.getQuantidade() - quantidade);
                    produtoDAO.AtualizarProdutoPorId(produtoPedido,produtoPedido.getId());

                    System.out.println("✅ Pedido realizado com sucesso!");
                    break;

                case 6:
                    List<Pedido> listaPedido = pedidoDAO.ListarPedidos();
                    for (Pedido p : listaPedido) {
                        Clientes cli = clientesDAO.buscarClientePorId(p.getIdCliente());
                        Produto prod = produtoDAO.BuscarProdutoPorId(p.getIdProduto());

                        System.out.println("ID do Pedido: " + p.getId());
                        if (cli != null) {
                            System.out.println("Cliente: " + cli.getNome() + " | Email: " + cli.getEmail());
                        } else {
                            System.out.println("Cliente não encontrado!");
                        }

                        if (prod != null) {
                            System.out.println("Produto: " + prod.getNome() + " | Preço: " + prod.getPreco());
                        } else {
                            System.out.println("Produto não encontrado!");
                        }

                        System.out.println("Quantidade: " + p.getQuantidade());
                        System.out.println("Data do Pedido: " + p.getDataPedido());
                        System.out.println("-----------------------------------");
                    }
                    break;


                case 7:
                    System.out.println("Digite o ID do pedido que deseja excluir: ");
                    int idExcluir = sc.nextInt();
                    boolean sucesso = pedidoDAO.ExcluirPedido(idExcluir);
                    if (sucesso) {
                        System.out.println("Pedido excluído com sucesso!");
                    } else {
                        System.out.println("Não foi possível excluir o pedido.");
                    }
                    break;

                case 8:
                    System.out.print("Digite o ID do pedido: ");
                    int idPedido = sc.nextInt();

                    System.out.print("Digite a nova quantidade: ");
                    int novaQuantidade = sc.nextInt();

                    boolean atualizado = pedidoDAO.AtualizarQuantidadePedido(idPedido, novaQuantidade);

                    if (atualizado) {
                        System.out.println("✅ Pedido atualizado com sucesso!");
                    } else {
                        System.out.println("❌ Erro ao atualizar o pedido.");
                    }
                    break;

                case 9:
                    System.out.println("ENCERRANDO PROGRAMA....");
                    break;

                case 10:
                    List<PedidoDetalhe> listaDetalhes = pedidoDAO.ListarPedidosComDetalhes();
                    if (listaDetalhes.isEmpty()) {
                        System.out.println("Nenhum pedido encontrado.");
                    } else {
                        System.out.println("\n--- PEDIDOS DETALHADOS ---");
                        for (PedidoDetalhe pd : listaDetalhes) {
                            System.out.println("ID Pedido: " + pd.getId());
                            System.out.println("Cliente: " + pd.getClienteNome() + " (ID: " + pd.getClienteId() + ")");
                            System.out.println("Produto: " + pd.getProdutoNome() + " (ID: " + pd.getProdutoId() + ")");
                            System.out.println("Quantidade: " + pd.getQuantidade());
                            System.out.println("Estoque Atual: " + pd.getEstoqueAtual());
                            System.out.println("Data do Pedido: " + pd.getDataPedido());
                            System.out.println("-----------------------------------");
                        }
                    }
                    break;

                default:
                    System.out.println("⚠ Opção inválida! Tente novamente.");
            }
        }

        sc.close();
    }
}
