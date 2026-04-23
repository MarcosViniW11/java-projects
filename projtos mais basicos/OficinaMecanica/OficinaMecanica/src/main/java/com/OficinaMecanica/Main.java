package com.OficinaMecanica;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        ServicoDAO servicoDAO = new ServicoDAO();
        Ordem_servicoDAO ordem_servicoDAO = new Ordem_servicoDAO();
        Item_servicoDAO item_servicoDAO = new Item_servicoDAO();

        Scanner sc = new Scanner(System.in);
        Boolean prosseguir=true;
        int opcaoPrincipal=0;

        while(prosseguir){
            System.out.println("MENU OFICINA MECANICA");
            System.out.println("1 - Para opções do cliente");
            System.out.println("2 - para opções de veiculo");
            System.out.println("3 - para opções de servico");
            System.out.println("4 - para opções de ordem de servico");
            System.out.println("5 - para opções de item servico");
            System.out.println("0 - Sair");

            opcaoPrincipal = sc.nextInt();
            sc.nextLine();
            switch (opcaoPrincipal){
                case 1:
                    System.out.println("Opções para cliente:");
                    System.out.println("1. para cadastrar");
                    System.out.println("2. para listar");
                    System.out.println("3. para remover");
                    System.out.println("4. para atualizar");
                    System.out.println("5. para Buscar por nome");
                    System.out.println("6. para buscar por id");

                    int opcaoCliente = sc.nextInt();
                    sc.nextLine();

                    switch (opcaoCliente){
                        case 1:
                            System.out.println("Digite o nome do cliente:");
                            String nome = sc.nextLine();
                            System.out.println("Digite o telefone: (DD)+ 18 numeros");
                            String telefone = sc.nextLine();
                            System.out.println("Digite o email:");
                            String email = sc.nextLine();
                            System.out.println("Digite o endereco:");
                            String endereco = sc.nextLine();
                            System.out.println("Digite o status do Cliente (Ativo/Inativo):");
                            String status = sc.nextLine();
                            Cliente cliente = new Cliente(nome, telefone, email, endereco, status);
                            clienteDAO.Cadastrar(cliente);
                            break;
                        case 2:
                            List<Cliente>listaDeCliente=clienteDAO.ListarClientes();
                            System.out.println("Lista de clientes:");
                            for(Cliente c:listaDeCliente){
                                System.out.println(c);
                            }
                            break;

                        case 3:
                            System.out.println("Digite o id do cliente que deseja remover:");
                            int idCliente = sc.nextInt();
                            sc.nextLine();
                            clienteDAO.excluirCliente(idCliente);
                            break;

                        case 4:
                            System.out.println("Digite o id do cliente que deseja atualizar:");
                            int idClienteAtual = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Digite o novo nome do cliente:");
                            nome = sc.nextLine();
                            System.out.println("Digite o novo telefone do cliente:(DD)+ 18 numeros");
                            telefone = sc.nextLine();
                            System.out.println("Digite o novo email do cliente:");
                            email = sc.nextLine();
                            System.out.println("Digite o novo endereco do cliente:");
                            endereco = sc.nextLine();
                            System.out.println("Digite o novo status do cliente:(Ativo/Inativo)");
                            status = sc.nextLine();
                            Cliente clienteAtualizar = new Cliente(nome, telefone, email, endereco, status);
                            clienteDAO.atualizarClientePorId(clienteAtualizar, idClienteAtual);
                            break;
                        case 5:
                            System.out.println("Digite o nome do cliente que deseja encontrar:");
                            nome = sc.nextLine();
                            List<Cliente> clienteEncontrado=clienteDAO.BuscarClientePorNome(nome);
                            for(Cliente c:clienteEncontrado){
                                System.out.println(c);
                            }
                            break;
                        case 6:
                            System.out.println("Digite o id do cliente que deseja encontrar:");
                            int idencontrar= sc.nextInt();
                            sc.nextLine();
                            Cliente clienteencontradoPorid=clienteDAO.BuscarClientePorId(idencontrar);
                            System.out.println(clienteencontradoPorid);
                            break;
                        default:
                            System.out.println("Digite uma opção valida");
                    }

                    break;

                case 2:
                    System.out.println("Opções para veiculo:");
                    System.out.println("1. para cadastrar um veiculo");
                    System.out.println("2. atualizar um veiculo");
                    System.out.println("3. para deletar um veiculo");
                    System.out.println("4. para listar veiculos");
                    System.out.println("5. para listar veiculos por cliente");
                    System.out.println("6. para Buscar veiculo por placa");
                    int opcaoVeiculo = sc.nextInt();
                    sc.nextLine();
                    switch (opcaoVeiculo) {
                        case 1:
                            System.out.println("Digite o id do cliente para o veiculo");
                            int idCliente = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Digite o nome do modelo do veiculo:");
                            String modelo = sc.nextLine();
                            System.out.println("Digite a placa do veiculo:(10 caracters)");
                            String placa = sc.nextLine();
                            System.out.println("Digite o ano do veiculo");
                            int ano = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Digite a status do veiculo:(Ativo/Inativo)");
                            String status = sc.nextLine();

                            Veiculo veiculo= new Veiculo(idCliente, modelo, placa, ano, status);
                            veiculoDAO.CadastrarVeiculo(veiculo);

                            break;
                        case 2:
                            System.out.println("Digite o id do veiculo que deseja atualizar:");
                            int idVeiculo = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Digite o novo id do cliente:");
                            int idClienteAtualizar = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Digite o novo modelo do veiculo");
                            String novoModelo = sc.nextLine();
                            System.out.println("digite a nova placa do veiculo:(10 caracters)");
                            String novaPlaca = sc.nextLine();
                            System.out.println("Digite o novo ano do veiculo");
                            int novoAno = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Digite o novo status do veiculo:(Ativo/Inativo)");
                            String novoStatus = sc.nextLine();
                            Veiculo novoVeiculo=new Veiculo(idClienteAtualizar,novoModelo,novaPlaca,novoAno, novoStatus);
                            veiculoDAO.AtualizarVeiculoPorId(novoVeiculo,idVeiculo);
                            break;
                        case 3:
                            System.out.println("Digite o id do veiculo que deseja remover:");
                            int idVeiculoRemover = sc.nextInt();
                            sc.nextLine();
                            veiculoDAO.ExcluirVeiculoPorId(idVeiculoRemover);
                            break;
                        case 4:
                            List<Veiculo> listaVeiculos=veiculoDAO.ListarVeiculos();
                            for(Veiculo v:listaVeiculos){
                                System.out.println(v);
                            }

                            break;
                        case 5:
                            System.out.println("Digite o id do cliente para visualizar os veiculos:");
                            int idVeiculoVisualizar = sc.nextInt();
                            sc.nextLine();
                            List<Veiculo> listaVeiculoPorCliente=veiculoDAO.ListarVeiculosPorCliente(idVeiculoVisualizar);
                            for(Veiculo v:listaVeiculoPorCliente){
                                System.out.println(v);
                            }

                            break;
                        case 6:
                            System.out.println("Digite a placa do veiculo:");
                            String placaBuscar = sc.nextLine();
                            Veiculo veiculoBucar=veiculoDAO.BuscarVeiculoPorPlaca(placaBuscar);
                            System.out.println(veiculoBucar);
                            break;
                        default:
                            System.out.println("Escolha uma opção valida");
                    }
                    break;
                case 3:
                    System.out.println("Opções para serviço");
                    System.out.println("1. para Cadastrar um serviço");
                    System.out.println("2. para Listar os serviços");
                    System.out.println("3. para Buscar um serviço");
                    System.out.println("4. para Atualizar serviço");
                    System.out.println("5. para Excluir serviço");
                    int opcaoServico = sc.nextInt();
                    sc.nextLine();

                    switch (opcaoServico) {
                        case 1:
                            System.out.println("Digite o nome do serviço");
                            String nomeServico = sc.nextLine();
                            System.out.println("Digite alguma observação referente ao serviço");
                            String observacaoServico = sc.nextLine();
                            System.out.println("Digite o valor do serviço");
                            double valorServico = sc.nextDouble();
                            Servico servico = new Servico(nomeServico, observacaoServico, valorServico);
                            servicoDAO.CadastrarServico(servico);
                            break;
                        case 2:
                            List<Servico> listaServicos=servicoDAO.ListarServicos();
                            for(Servico serv:listaServicos){
                                System.out.println(serv);
                            }
                            break;
                        case 3:
                            System.out.println("Digite o id do serviço que deseja procurar");
                            int idServicoBuscar = sc.nextInt();
                            sc.nextLine();
                            Servico servicoBuscar=servicoDAO.BuscarServicoPorId(idServicoBuscar);
                            System.out.println(servicoBuscar);
                            break;
                        case 4:
                            System.out.println("Digite o id do serviço que deseja atualizar");
                            int idServicoAtualizar = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Digite o novo nome do serviço");
                            String novoNomeServico = sc.nextLine();
                            System.out.println("Digite uma nova observação referente ao serviço");
                            String novaObservacaoServico = sc.nextLine();
                            System.out.println("Digite o novo valor do serviço");
                            double valorServicoServico = sc.nextDouble();
                            Servico servicoAtualizar=new Servico(novoNomeServico, novaObservacaoServico, valorServicoServico);
                            servicoDAO.AtualizarServicoPorId(servicoAtualizar,idServicoAtualizar);
                            break;
                        case 5:
                            System.out.println("Digite o id do serviço que deseja remover");
                            int idServicoRemover = sc.nextInt();
                            sc.nextLine();
                            servicoDAO.ExcluirServicoPorId(idServicoRemover);
                            break;
                        default:
                            System.out.println("Escolha uma opção valida");
                    }
                    break;

                case 4:
                    System.out.println("Opções para ordem de Serviço");
                    System.out.println("1. para cadastrar um OS");
                    System.out.println("2. para Listar OS");
                    System.out.println("3. para Buscar OS");
                    System.out.println("4. para Atualizar OS");
                    System.out.println("5. para atualizar o Status de uma OS");
                    System.out.println("6. para Excluir OS");
                    int opcaoOrdemDeServico = sc.nextInt();
                    sc.nextLine();

                    switch (opcaoOrdemDeServico) {
                        case 1:
                            Date datainicio = new Date(System.currentTimeMillis());
                            System.out.println("Digite o id do Cliente");
                            int idCliente= sc.nextInt();
                            sc.nextLine();
                            System.out.println("Digite o id do veiculo");
                            int idVeiculo= sc.nextInt();
                            sc.nextLine();
                            double total=0.00;
                            System.out.println("Digite qual seria a data prevista para o fechamento da OS");
                            System.out.println("1-para um dia\n2-para uma semana\n3-para um mês\n4-para um ano");
                            int escolha = sc.nextInt();
                            sc.nextLine();
                            Date dataFim =null;
                            switch (escolha) {
                                case 1:
                                    dataFim=new Date(System.currentTimeMillis()+(1L*24*60*60*1000));
                                    break;
                                case 2:
                                    dataFim=new Date(System.currentTimeMillis()+(7L*24*60*60*1000));
                                    break;
                                case 3:
                                    dataFim=new Date(System.currentTimeMillis()+(30L*24*60*60*1000));
                                    break;
                                case 4:
                                    dataFim=new Date(System.currentTimeMillis()+(365L*24*60*60*1000));
                                    break;
                            }

                            System.out.println("Digite o Status da OS (Aberta/Em Andamento/Concluida)");
                            String status=sc.nextLine();
                            System.out.println("Digite alguma Observação referente a OS");
                            String observacoes=sc.nextLine();
                            Ordem_servico ordemDAO = new Ordem_servico(idCliente,idVeiculo,total,datainicio,dataFim,status,observacoes);
                            ordem_servicoDAO.CriarOS(ordemDAO);
                            break;
                        case 2:
                            List<Ordem_servico> listaOrdemServicos = ordem_servicoDAO.ListarOS();
                            for (Ordem_servico ordem_servico : listaOrdemServicos) {
                                System.out.println(ordem_servico);
                            }
                            break;
                        case 3:
                            System.out.println("Digite o id da OS que deseja Buscar");
                            int idOSBuscar = sc.nextInt();
                            sc.nextLine();
                            Ordem_servico ordemServicoBuscar=ordem_servicoDAO.BuscarOSPorId(idOSBuscar);
                            System.out.println(ordemServicoBuscar);
                            break;
                        case 4:
                            System.out.println("Digite o id da OS que deseja Atualizar");
                            int idOSAtualizar = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Digite o id do novo cliente");
                            int idNovoCliente = sc.nextInt();
                            System.out.println("Digite o id do novo veiculo");
                            int idNovoVeiculo = sc.nextInt();
                            System.out.println("Digite o novo total:");
                            double novoTotal = sc.nextDouble();
                            Date dataAbertura=new Date(System.currentTimeMillis());
                            System.out.println("Escolha a nova Data de fechamento da OS");
                            System.out.println("1-para um dia\n2-para uma semana\n3-para um mês\n4-para um ano");
                            int EscolhaDataFechamento = sc.nextInt();
                            Date dataDeFechamento=null;
                            sc.nextLine();
                            switch (EscolhaDataFechamento) {
                                case 1:
                                    dataDeFechamento=new Date(System.currentTimeMillis()+(1L*24*60*60*1000));
                                    break;
                                case 2:
                                    dataDeFechamento=new Date(System.currentTimeMillis()+(7L*24*60*60*1000));
                                    break;
                                case 3:
                                    dataDeFechamento=new Date(System.currentTimeMillis()+(30L*24*60*60*1000));
                                    break;
                                case 4:
                                    dataDeFechamento=new Date(System.currentTimeMillis()+(365L*24*60*60*1000));
                                    break;
                            }
                            System.out.println("Digite o novo Status da OS:");
                            String novoStatus=sc.nextLine();
                            System.out.println("Digite alguma nova observação:");
                            String novoObservacoes=sc.nextLine();
                            Ordem_servico OrdemServicoAtualizar=new Ordem_servico(idNovoCliente,idNovoVeiculo,novoTotal,dataAbertura,dataDeFechamento,novoStatus,novoObservacoes);
                            ordem_servicoDAO.AtualizarOS(OrdemServicoAtualizar,idOSAtualizar);
                            break;
                        case 5:
                            System.out.println("Digite o id da OS que deseja Atualizar seu Status");
                            int idOSAtualizarStatus=sc.nextInt();
                            sc.nextLine();
                            System.out.println("Digite o novo Status que deseja colocar");
                            String novoStatusColocar=sc.nextLine();
                            ordem_servicoDAO.AlterarStatusDaOSPorId(novoStatusColocar,idOSAtualizarStatus);
                            break;
                        case 6:
                            System.out.println("Digite o id da OS que deseja remover");
                            int idOSRemover=sc.nextInt();
                            ordem_servicoDAO.ExcluirOSPorId(idOSRemover);
                            break;
                        default:
                            System.out.println("Digite uma opção valida");
                    }
                    break;
                case 5:
                    System.out.println("Opções para item Seviço");
                    System.out.println("1. para Adicionar um item serviço");
                    System.out.println("2. para excluir um item serviço");
                    System.out.println("3. para Listar item serviços por OS");
                    System.out.println("4. para Buscar um item serviço");
                    System.out.println("5. para Calcular o total de items por OS");
                    System.out.println("6. para atualizar quantidade de um item serviço");
                    System.out.println("7. para Adicionar e atualizar total da OS");

                    int opcaoItemServico=sc.nextInt();
                    sc.nextLine();
                    switch (opcaoItemServico) {
                        case 1:
                            System.out.println("Digite o id da OS que deseja adicionar");
                            int idOS=sc.nextInt();
                            System.out.println("Digite o id do serviço");
                            int idServico=sc.nextInt();
                            System.out.println("Digite a quantidade do serviço que deseja fazer");
                            int quantidade=sc.nextInt();
                            Item_servico itemServico=new Item_servico(idOS,idServico,quantidade);
                            item_servicoDAO.adicionarItem(itemServico);
                            break;
                        case 2:
                            System.out.println("Digite o id do item serviço que deseja remover");
                            int iditemRemover=sc.nextInt();
                            item_servicoDAO.excluirItem(iditemRemover);
                            break;
                        case 3:
                            System.out.println("Digite o id da OS que deseja Listar");
                            int idOSListar=sc.nextInt();
                            sc.nextLine();
                            List<Item_servico> listaItemServicos=item_servicoDAO.ListarItem_servicoPorOS(idOSListar);
                            for (Item_servico item_servico : listaItemServicos) {
                                System.out.println(item_servico);
                            }
                            break;
                        case 4:
                            System.out.println("Digite o id do item serviço que deseja buscar");
                            int iditemBuscar=sc.nextInt();
                            Item_servico item_servicoBuscar=item_servicoDAO.BucarItem_servicoPorId(iditemBuscar);
                            System.out.println("Serviço encontrado: "+item_servicoBuscar);
                            break;
                        case 5:
                            System.out.println("Digite o id da OS que deseja somar o total");
                            int idOSSomar=sc.nextInt();
                            double totalDaOS=item_servicoDAO.CalcularTotalPorOS(idOSSomar);
                            System.out.println("O total da OS que vc digitou foi de "+ totalDaOS);
                            break;
                        case 6:
                            System.out.println("Digite o id do item serviço que deseja alterar a quantidade");
                            int idOSAlterar=sc.nextInt();
                            System.out.println("Digite a nova quantidade");
                            int quantidadeAlterar=sc.nextInt();
                            item_servicoDAO.atualizarQuantidade(idOSAlterar,quantidadeAlterar);
                            break;
                        case 7:
                            System.out.println("Digite o id da OS");
                            int idOs=sc.nextInt();
                            sc.nextLine();
                            System.out.println("Digite o id do serviço");
                            int idservico=sc.nextInt();
                            System.out.println("Digite a quantidade");
                            int Quantidade=sc.nextInt();
                            Item_servico item_servico=new Item_servico(idOs,idservico,Quantidade);
                            item_servicoDAO.adicionarItemEAtualizartotalDaOs(item_servico);
                            break;
                    }



                    break;
                case 0:
                    System.out.println("Fechando o sistema... até logo...");
                    prosseguir=false;
                    break;
                default:
                    System.out.println("Escolha uma opção valida");
            }


        }








    }
}