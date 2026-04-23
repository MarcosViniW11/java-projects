package com.OficinaMecanica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Item_servicoDAO {

    public boolean adicionarItem(Item_servico item_servico){
        String sqlSelect="SELECT valor FROM servico WHERE id=?";
        String sqlInsert="INSERT INTO item_servico (idOrdemServico,idServico,quantidade,valorUnitario,subtotal) VALUES (?,?,?,?,?)";
        String sqlUpdate="UPDATE ordem_servico SET total=total+? WHERE id=?";

        try(Connection conn=Conexao.getConexao()){
            conn.setAutoCommit(false);
            int idOrdemServico=0;
            double total=0;
            int idServico=item_servico.getIdServico();
            double valorServico=0.00;

            try(PreparedStatement ps=conn.prepareStatement(sqlSelect)){
                ps.setInt(1,idServico);
                ResultSet rs=ps.executeQuery();
                if(rs.next()){
                    valorServico=rs.getDouble("valor");
                }
            }

            try(PreparedStatement ps = conn.prepareStatement(sqlInsert,PreparedStatement.RETURN_GENERATED_KEYS)){
                //double valorUnitario=item_servico.getValorUnitario();
                int quantidade=item_servico.getQuantidade();
                double subtotal=quantidade*valorServico;
                total=quantidade*valorServico;
                ps.setInt(1,item_servico.getIdOrdemServico());
                ps.setInt(2,item_servico.getIdServico());
                ps.setInt(3,item_servico.getQuantidade());
                ps.setDouble(4,valorServico);
                ps.setDouble(5,subtotal);
                ps.executeUpdate();
                idOrdemServico=item_servico.getIdOrdemServico();

                try(ResultSet rs=ps.getGeneratedKeys()){
                    if(!rs.next()){
                        System.out.println("OCORREU UM ERRO AO CADASTRAR item servico");
                        conn.rollback();
                        return false;
                    }
                    item_servico.setId(1);
                }
                System.out.println("Ordem servico Cadastrado com sucesso");

            }

            try(PreparedStatement ps = conn.prepareStatement(sqlUpdate)){
                ps.setDouble(1,total);
                ps.setInt(2,idOrdemServico);

                int afetada = ps.executeUpdate();
                if(afetada>0){
                    System.out.println("Total de Ordem de serviço atualizada com sucesso");
                }

            }

            System.out.println("Tudo Certo");
            conn.commit();
            return true;


        } catch (Exception e) {
            System.out.println("Erro ao adicionar Item_servico");
            return false;
        }

    }

    public boolean excluirItem(int id){
        String sqlSelect="SELECT idOrdemServico,subtotal FROM item_servico WHERE id=?";
        String sqlUpdate="UPDATE ordem_servico SET total=total-? WHERE id=?";
        String sqlDelect="DELETE FROM item_servico WHERE id=?";

        try(Connection conn=Conexao.getConexao()){
            conn.setAutoCommit(false);
            int idOdemServico=0;
            double subtotal=0;

            try(PreparedStatement ps=conn.prepareStatement(sqlSelect)){
                ps.setInt(1,id);
                ResultSet rs=ps.executeQuery();
                if(!rs.next()){
                    System.out.println("Erro ao dar o SELECT Do item_servico");
                    conn.rollback();
                    return false;
                }
                idOdemServico=rs.getInt("idOrdemServico");
                subtotal=rs.getDouble("subtotal");
                System.out.println("DADOS DO SELECT COLETADOS");
            }

            try(PreparedStatement ps = conn.prepareStatement(sqlUpdate)){
                ps.setDouble(1,subtotal);
                ps.setInt(2,idOdemServico);
                int afetada = ps.executeUpdate();
                if(afetada>0){
                    System.out.println("DADOS ATUALIZADOS Na ordem_servico com sucesso");
                }else{
                    System.out.println("Erro ao atualizar Item_servico");
                    conn.rollback();
                    return false;
                }
            }

            try(PreparedStatement ps = conn.prepareStatement(sqlDelect)){
                ps.setInt(1,id);
                int afetada = ps.executeUpdate();
                if(afetada>0){
                    System.out.println("DADOS DO item SERVIÇO DELETADOS COM SUCESSO");
                }else {
                    System.out.println("ERRO AO DELETAR SERVIÇO");
                    conn.rollback();
                    return false;
                }
            }
            System.out.println("Tudo Certo");
            conn.commit();
            return true;


        } catch (Exception e) {
            System.out.println("Erro ao excluir Item_servico"+e.getMessage());
            return false;
        }


    }

    public List<Item_servico> ListarItem_servicoPorOS(int idOs){
        String sqlSelect="SELECT * FROM item_servico WHERE idOrdemServico=?";
        List<Item_servico> listaItem_servicos=new ArrayList<>();
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sqlSelect)){
            ps.setInt(1,idOs);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Item_servico item_servico=new Item_servico(rs.getInt("idOrdemServico"),rs.getInt("idServico"),
                        rs.getInt("quantidade"),rs.getDouble("valorUnitario"),rs.getDouble("subtotal"));
                item_servico.setId(rs.getInt("id"));
                listaItem_servicos.add(item_servico);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar Item_servico"+e.getMessage());
        }
        return listaItem_servicos;
    }

    public Item_servico BucarItem_servicoPorId(int id){
        String sqlSelect="SELECT * FROM item_servico WHERE id=?";
        Item_servico item_servico=new Item_servico();
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sqlSelect)){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                item_servico.setId(rs.getInt("id"));
                item_servico.setIdOrdemServico(rs.getInt("idOrdemServico"));
                item_servico.setIdServico(rs.getInt("idServico"));
                item_servico.setQuantidade(rs.getInt("quantidade"));
                item_servico.setValorUnitario(rs.getDouble("valorUnitario"));
                item_servico.setSubtotal(rs.getDouble("subtotal"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao Buscar Item_servico Pelo id:"+e.getMessage());
        }
        return item_servico;
    }

    public double CalcularTotalPorOS(int idOs){
        String slqSum="SELECT SUM(subtotal) FROM item_servico WHERE idOrdemServico=?";
        double valorTotal=0;
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(slqSum)){
            ps.setInt(1,idOs);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                valorTotal=rs.getDouble("SUM(subtotal)");
            }

        } catch (Exception e) {
            System.out.println("Erro ao calcular Total por OS:"+e.getMessage()  );
        }
        return valorTotal;
    }

    public boolean atualizarQuantidade(int idItem_servico, int NovaQTB){
        String sqlItem_servico="SELECT * FROM item_servico WHERE id=?";
        String sqlUpdateItem_servico="UPDATE item_servico SET quantidade=?,subtotal=? WHERE id=?";

        try(Connection conn=Conexao.getConexao();){
            conn.setAutoCommit(false);
            double valorUnitario=0.00;
            double subtotal=0.00;


            try(PreparedStatement ps=conn.prepareStatement(sqlItem_servico)){
                ps.setInt(1, idItem_servico);
                ResultSet rs=ps.executeQuery();
                if(!rs.next()){
                    System.out.println("Houve um erro ao pegar as informaçoes do select");
                    conn.rollback();
                    return false;
                }
                valorUnitario=rs.getDouble("valorUnitario");
                subtotal=valorUnitario*NovaQTB;
            }

            try(PreparedStatement ps=conn.prepareStatement(sqlUpdateItem_servico)){
                ps.setInt(1, NovaQTB);
                ps.setDouble(2, subtotal);
                ps.setInt(3, idItem_servico);
                int afetadas=ps.executeUpdate();
                if(afetadas>0){
                    System.out.println("Atualizado com sucesso");
                }
            }

            System.out.println("Quantidade atualizada com sucesso");
            conn.commit();
            return true;


        } catch (Exception e) {
            System.out.println("Erro ao atualizar Item_servico"+e.getMessage());
            return false;
        }

    }

    public boolean adicionarItemEAtualizartotalDaOs(Item_servico item_servico){
        String sqlselectServico="SELECT valor from servico WHERE id=?";
        String sqlInsert="INSERT INTO item_servico (idOrdemServico,idServico,quantidade,valorUnitario,subtotal) VALUES (?,?,?,?,?)";
        String UpdateOS="UPDATE ordem_servico SET total=total+? WHERE id=?";

        try(Connection conn=Conexao.getConexao()){
            conn.setAutoCommit(false);
            double valorUnitario=0.00;
            double subtotal=0.00;
            int idOrdemServico=item_servico.getIdOrdemServico();
            int idServico=item_servico.getIdServico();
            int quantidade=item_servico.getQuantidade();

            try(PreparedStatement ps=conn.prepareStatement(sqlselectServico)){
                ps.setInt(1, idServico);
                ResultSet rs=ps.executeQuery();
                if(!rs.next()){
                    System.out.println("Houve um erro ao buscar pelo serviço");
                    conn.rollback();
                    return false;
                }
                valorUnitario=rs.getDouble("valor");
                subtotal=valorUnitario*quantidade;
            }

            try(PreparedStatement ps=conn.prepareStatement(sqlInsert)){
                ps.setInt(1, idOrdemServico);
                ps.setInt(2, idServico);
                ps.setInt(3, quantidade);
                ps.setDouble(4, valorUnitario);
                ps.setDouble(5, subtotal);

                int afetadas=ps.executeUpdate();
                if(afetadas>0){
                    System.out.println("item servico adicionado com sucesso");
                }

            }

            try(PreparedStatement ps=conn.prepareStatement(UpdateOS)){
                ps.setDouble(1, subtotal);
                ps.setInt(2, idOrdemServico);

                int afetadas=ps.executeUpdate();
                if(afetadas>0){
                    System.out.println("Valor total da OS Recalculada com sucesso");
                }

            }

            System.out.println("Tudo certo");
            conn.commit();
            return true;


        } catch (Exception e) {
            System.out.println("Erro ao adicionar e/ou atualizar"+e.getMessage());
            return false;
        }

    }



}
