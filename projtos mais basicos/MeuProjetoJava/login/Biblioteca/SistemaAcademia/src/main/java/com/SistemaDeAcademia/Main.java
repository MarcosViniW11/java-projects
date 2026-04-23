package com.SistemaDeAcademia;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        AlunoDAO alunoDAO = new AlunoDAO();
        TreinoDAO treinoDAO = new TreinoDAO();
        ExercicioDAO exercicioDAO = new ExercicioDAO();
        PagamentoDAO pagamentoDAO = new PagamentoDAO();
        Scanner sc = new Scanner(System.in);

        int opcao=10;

        while(opcao!=0){
            System.out.println("SISTEMA DE ACADEMIA");
            System.out.println("0. para sair");
            System.out.println("1. para opções de Aluno");
            System.out.println("2. para opções de treino");
            System.out.println("3. para opções de exercicio");
            System.out.println("4. para opções de pagamento");


            opcao = sc.nextInt();
            switch(opcao){
                case 1:
                    System.out.println("OPÇÃO DE ALUNO!!");
                    System.out.println("1 - PARA CADASTRAR ALUNO");
                    System.out.println("2 - PARA LISTAR ALUNO");
                    System.out.println("3 - PARA ATUALIZAR ALUNO");
                    System.out.println("4 - PARA EXCLUIR ALUNO");

                    int opcao2 = sc.nextInt();
                    sc.nextLine();

                    switch(opcao2){
                        case 1:
                            System.out.println("Digite o nome do aluno: ");
                            String nome = sc.nextLine();
                            System.out.println("Digite o idade do aluno: ");
                            int idade = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Digite o plano do aluno: ");
                            String plano = sc.nextLine();
                            System.out.println("Digite o status do aluno: ");
                            String status = sc.nextLine();

                            Aluno aluno = new Aluno(nome, idade, plano, status);
                            alunoDAO.CadastrarAluno(aluno);
                            break;
                        case 2:
                            List<Aluno> alunos = alunoDAO.ListarAlunos();
                            for(Aluno al:alunos){
                                System.out.println(al);
                            }

                            break;
                        case 3:
                            System.out.println("digite o id do aluno que deseja atualizar: ");
                            int id = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Digite o novo nome do aluno: ");
                            nome = sc.nextLine();
                            System.out.println("Digite a nova idade do aluno: ");
                            int idade2 = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Digite o novo plano do aluno: ");
                            String plano2 = sc.nextLine();
                            System.out.println("Digite o novo status do aluno: ");
                            String status2 = sc.nextLine();
                            Aluno alunoAtualizar= new Aluno(nome, idade2, plano2, status2);
                            alunoDAO.atualizarAluno(alunoAtualizar,id);
                            break;

                        case 4:
                            System.out.println("digite o id do aluno que deseja Excluir: ");
                            int idExcluir = sc.nextInt();
                            sc.nextLine();
                            alunoDAO.excluirAluno(idExcluir);
                            break;

                        default:
                            System.out.println("DIGITE UMA OPÇÃO VALIDA");
                    }


                    break;

               case 2:
                   System.out.println("1 - para Cadastrar treino");
                   System.out.println("2 - para Listar treinos");
                   System.out.println("3 - para atualizar treino");
                   System.out.println("4 - para excluir treino");
                   int opcao3 = sc.nextInt();
                   sc.nextLine();

                   switch(opcao3){
                       case 1:
                           System.out.println("Digite o id do Aluno: ");
                           int idAluno = sc.nextInt();
                           sc.nextLine();
                           System.out.println("Digite a descrição do treino: ");
                           String descricao = sc.nextLine();
                           System.out.println("Digite a data de Inicio (formato: yyyy-MM-dd): )");
                           String dataInicioStr = sc.nextLine();
                           System.out.println("Digite a data de Fim (formato: yyyy-MM-dd): ");
                           String dataFimStr = sc.nextLine();

                           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                           Date dataInicio=sdf.parse(dataInicioStr.trim());
                           Date dataFim=sdf.parse(dataFimStr.trim());

                           Treino treino=new Treino(idAluno,descricao,dataInicio,dataFim);

                           treinoDAO.CadastrarTreino(treino);
                            break;

                       case 2:
                           System.out.println("Digite o id do Aluno que deseja visualizar os treinos: ");
                           int idAluno2 = sc.nextInt();
                           sc.nextLine();
                           List<Treino> listaTreino=treinoDAO.ListarTreinoDeUmAluno(idAluno2);
                           for(Treino t:listaTreino){
                               System.out.println(t);
                           }

                           break;

                       case 3:
                           System.out.println("Digite o id do Treino que deseja atualizar: ");
                           int idTreino = sc.nextInt();
                           sc.nextLine();
                           System.out.println("Digite o novo id do aluno: ");
                           int idAlunoAtualizar = sc.nextInt();
                           sc.nextLine();
                           System.out.println("Digite a nova descrição do treino: ");
                           String descricao2 = sc.nextLine();

                           System.out.println("Digite a data de Inicio (formato: yyyy-MM-dd): )");
                           String datainicioStr02 = sc.nextLine();
                           System.out.println("Digite a data de Fim (formato: yyyy-MM-dd): ");
                           String datafimStr02 = sc.nextLine();

                           SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                           Date dataInicio2 = sdf2.parse(datainicioStr02.trim());
                           Date dataFim2 = sdf2.parse(datafimStr02.trim());
                           Treino treinoAtualizado=new Treino(idAlunoAtualizar,descricao2,dataInicio2,dataFim2);
                           treinoDAO.atualizarTreino(treinoAtualizado,idTreino);

                           break;

                       case 4:
                           System.out.println("Digite o id do treino que deseja excluir: ");
                           int idTreinoExcluir = sc.nextInt();
                           sc.nextLine();
                           treinoDAO.excluirTreino(idTreinoExcluir);

                           break;

                       default:
                           System.out.println("Escolha uma opção valida");



                   }


                   break;

               case 3:
                   System.out.println("1 - para Cadastrar Treino");
                   System.out.println("2 - para Listar Treino");
                   System.out.println("3 - para Atualizar Treino");
                   System.out.println("4 - para Excluir Treino");
                   int opcao4 = sc.nextInt();
                   sc.nextLine();

                   switch(opcao4){
                       case 1:
                           System.out.println("Digite o id do treino que deseja cadastrar o exercicio:");
                           int idTreino = sc.nextInt();
                           sc.nextLine();
                           System.out.println("Digite o nome do treino: ");
                           String nomeTreino = sc.nextLine();
                           System.out.println("Digite a quantidade de series do exercicio: ");
                           int series = sc.nextInt();
                           sc.nextLine();
                           System.out.println("Digite a quantidade de repetcoes do exercicio: ");
                           int repetcoes = sc.nextInt();
                           sc.nextLine();
                           System.out.println("Digite alguma observacao do exercicio: ");
                           String observacao = sc.nextLine();

                           Exercicio exercicio=new Exercicio(idTreino,nomeTreino,series,repetcoes,observacao);
                           exercicioDAO.CadastrarExercicio(exercicio);

                           break;

                       case 2:
                           System.out.println("LISTA DE EXERCICIOS");
                           System.out.println("Digite o id do treino para visualizar todos os exercicios: ");
                           int idTreinoExercicio = sc.nextInt();
                           sc.nextLine();
                           List<Exercicio> listaExercicio=exercicioDAO.ListarExercicio(idTreinoExercicio);
                           for(Exercicio ex:listaExercicio){
                               System.out.println(ex);
                           }

                           break;

                       case 3:
                           System.out.println("Digite o id do exercicio que deseja atualizar: ");
                           int idExercicioAtualizar = sc.nextInt();
                           sc.nextLine();
                           System.out.println("Digite o novo id de treino :");
                           int idTreinoAtualizar = sc.nextInt();
                           sc.nextLine();
                           System.out.println("Digite o novo nome do exercicio: ");
                           String nomeNovoExercicio = sc.nextLine();
                           System.out.println("Digite a nova quantidade de series do exercicio: ");
                           int seriesNovo = sc.nextInt();
                           sc.nextLine();
                           System.out.println("Digite a nova quantidade de repetições do exercicio: ");
                           int repetcoesNovo = sc.nextInt();
                           sc.nextLine();
                           System.out.println("Digite alguma nova observacao do exercicio: ");
                           String observacaoNovoExercicio = sc.nextLine();

                           Exercicio novoExercicio=new Exercicio(idTreinoAtualizar,nomeNovoExercicio,seriesNovo,repetcoesNovo,observacaoNovoExercicio);
                           exercicioDAO.AtualizarExercicio(novoExercicio,idExercicioAtualizar);


                           break;

                       case 4:
                           System.out.println("Digite o id do exercicio que deseja deletar: ");
                           int idExercicioDeletar = sc.nextInt();
                           sc.nextLine();
                           exercicioDAO.ExcluirExercicio(idExercicioDeletar);


                           break;


                       default:
                           System.out.println("Escolha uma opcao valida");
                   }


                    break;

                case 4:

                    System.out.println("=== OPÇÕES DE PAGAMENTO ===");
                    System.out.println("1 - Cadastrar Pagamento");
                    System.out.println("2 - Listar Todos os Pagamentos");
                    System.out.println("3 - Listar Pagamentos de um Aluno");
                    System.out.println("4 - Atualizar Pagamento");
                    System.out.println("5 - Excluir Pagamento");
                    System.out.println("6 - Atualizar Status de Pagamentos Vencidos");
                    int opcaoPagamento = sc.nextInt();
                    sc.nextLine();

                    switch (opcaoPagamento){
                        case 1:
                            System.out.println("Digite o id do Aluno :");
                            int idAluno = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Digite o valor :");
                            double valor = sc.nextDouble();
                            sc.nextLine();
                            System.out.println("Digite a data de pagamento (formato: yyyy-MM-dd): ");
                            String dataPagStr = sc.nextLine();

                            System.out.println("Digite a data de vencimento (formato: yyyy-MM-dd): ");
                            String dataVencStr = sc.nextLine();

                            SimpleDateFormat sdfPag = new SimpleDateFormat("yyyy-MM-dd");
                            Date dataPagamento = sdfPag.parse(dataPagStr);
                            Date dataVencimento = sdfPag.parse(dataVencStr);

                            String statusPag;
                            Date hoje=new Date();
                            if(dataPagamento.after(dataVencimento)){
                                statusPag="Vencido";
                            } else if (dataPagamento.before(hoje) || dataPagamento.equals(hoje)) {
                                statusPag="Pago";
                            }else {
                                statusPag="Pendente";
                            }

                            Pagamento pagamento=new Pagamento(idAluno,valor,dataPagamento,dataVencimento,statusPag);
                            pagamentoDAO.CadastrarPagamento(pagamento);
                            break;

                        case 2:
                            System.out.println("=== LISTA DE TODOS OS PAGAMENTOS ===");
                            List<Pagamento> pagamentos = pagamentoDAO.ListarPagamentos();
                            for (Pagamento p : pagamentos) {
                                System.out.println(p);
                            }
                            break;

                        case 3:
                            System.out.println("Digite o ID do aluno para listar pagamentos: ");
                            int idAlunoBusca = sc.nextInt();
                            sc.nextLine();
                            List<Pagamento> pagamentosAluno = pagamentoDAO.ListarPagamentoPorAluno(idAlunoBusca);
                            for (Pagamento p : pagamentosAluno) {
                                System.out.println(p);
                            }

                            break;

                        case 4:
                            System.out.println("Digite o ID do pagamento que deseja atualizar: ");
                            int idPagAtualizar = sc.nextInt();
                            sc.nextLine();

                            System.out.println("Digite o novo ID do aluno: ");
                            int novoIdAluno = sc.nextInt();
                            sc.nextLine();

                            System.out.println("Digite o novo valor do pagamento: ");
                            double novoValor = sc.nextDouble();
                            sc.nextLine();

                            System.out.println("Digite a nova data de pagamento (yyyy-MM-dd): ");
                            String novaDataPagStr = sc.nextLine();

                            System.out.println("Digite a nova data de vencimento (yyyy-MM-dd): ");
                            String novaDataVencStr = sc.nextLine();

                            SimpleDateFormat sdfPag02 = new SimpleDateFormat("yyyy-MM-dd");
                            Date novaDataPagamento = sdfPag02.parse(novaDataPagStr.trim());
                            Date novaDataVencimento = sdfPag02.parse(novaDataVencStr.trim());

                            System.out.println("Digite o novo status (Pago, Pendente ou Vencido): ");
                            String novoStatus = sc.nextLine();

                            Pagamento pagamentoAtualizado = new Pagamento(novoIdAluno, novoValor, novaDataPagamento, novaDataVencimento, novoStatus);
                            pagamentoDAO.AtualizarPagamento(pagamentoAtualizado, idPagAtualizar);
                            break;

                        case 5:
                            System.out.println("Digite o ID do pagamento que deseja excluir: ");
                            int idPagExcluir = sc.nextInt();
                            sc.nextLine();
                            pagamentoDAO.ExcluirPagamento(idPagExcluir);
                            break;

                        case 6:
                            pagamentoDAO.AtualizarStatusPagamentosVencidos();
                            break;

                        default:
                            System.out.println("Opção inválida!");
                            break;
                    }



                    break;

                case 0:
                    System.out.println("Finalizando programa...");
                    opcao = 0;
                    break;
                default:
                    System.out.println("opção invalida!");


            }


        }




    }
}