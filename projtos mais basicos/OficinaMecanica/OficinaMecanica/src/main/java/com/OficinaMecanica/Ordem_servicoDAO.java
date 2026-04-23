package com.OficinaMecanica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Ordem_servicoDAO {

    public void CriarOS(Ordem_servico ordem_servico){
        String sql="INSERT INTO ordem_servico (idCliente,idVeiculo,total,dataAbertura,dataFechamento,statos,observacoes) VALUES (?,?,?,?,?,?,?)";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
            ps.setInt(1,ordem_servico.getIdCliente());
            ps.setInt(2,ordem_servico.getIdVeiculo());
            ps.setDouble(3,ordem_servico.getTotal());
            ps.setDate(4, new java.sql.Date(ordem_servico.getDataAbertura().getTime()));
            ps.setDate(5, new java.sql.Date(ordem_servico.getDataFechamento().getTime()));
            ps.setString(6,ordem_servico.getStatus());
            ps.setString(7,ordem_servico.getObservacoes());
            ps.executeUpdate();

            try(ResultSet rs=ps.getGeneratedKeys()){
                if(rs.next()){
                    ordem_servico.setId(rs.getInt(1));
                }
            }
            System.out.println("OS Criada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao criar o ordem_servico"+e.getMessage());
        }
    }

    public List<Ordem_servico> ListarOS(){
        String sql="SELECT * FROM ordem_servico";
        List<Ordem_servico> ListaOrdemServicos=new ArrayList<>();
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);ResultSet rs=ps.executeQuery();){
            while(rs.next()){
                Ordem_servico ordemServico=new Ordem_servico(rs.getInt("idCliente"),rs.getInt("idVeiculo"),rs.getDouble("total"),rs.getDate("dataAbertura"),rs.getDate("dataFechamento"),rs.getString("statos"),rs.getString("observacoes"));
                ordemServico.setId(rs.getInt("id"));
                ListaOrdemServicos.add(ordemServico);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar o ordem_servico"+e.getMessage());
        }
        return ListaOrdemServicos;
    }

    public Ordem_servico BuscarOSPorId(int id){
        String sql="SELECT * FROM ordem_servico WHERE id=?";
        Ordem_servico ordemServico=new Ordem_servico();
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                ordemServico.setId(rs.getInt("id"));
                ordemServico.setIdCliente(rs.getInt("idCliente"));
                ordemServico.setIdVeiculo(rs.getInt("idVeiculo"));
                ordemServico.setTotal(rs.getDouble("total"));
                ordemServico.setDataAbertura(rs.getDate("dataAbertura"));
                ordemServico.setDataFechamento(rs.getDate("dataFechamento"));
                ordemServico.setStatus(rs.getString("statos"));
                ordemServico.setObservacoes(rs.getString("observacoes"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar o ordem_servico"+e.getMessage());
        }
        return ordemServico;
    }

    public void AtualizarOS(Ordem_servico ordemServico,int id){
        String sql="UPDATE ordem_servico SET idCliente=?,idVeiculo=?,total=?,dataAbertura=?,dataFechamento=?,statos=?,observacoes=? WHERE id=?";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,ordemServico.getIdCliente());
            ps.setInt(2,ordemServico.getIdVeiculo());
            ps.setDouble(3,ordemServico.getTotal());
            ps.setDate(4, new java.sql.Date(ordemServico.getDataAbertura().getTime()));
            ps.setDate(5, new java.sql.Date(ordemServico.getDataFechamento().getTime()));
            ps.setString(6,ordemServico.getStatus());
            ps.setString(7,ordemServico.getObservacoes());
            ps.setInt(8,id);

            int afetadas=ps.executeUpdate();
            if(afetadas>0){
                System.out.println("OS atualizada com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizar o ordem_servico"+e.getMessage());
        }
    }

    public void AlterarStatusDaOSPorId(String novoStatus,int id){
        String sql="UPDATE ordem_servico SET statos=? WHERE id=?";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setString(1,novoStatus);
            ps.setInt(2,id);
            int afetadas=ps.executeUpdate();
            if(afetadas>0){
                System.out.println("Status da OS atualizada com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizar o ordem_servico"+e.getMessage());
        }

    }

    public void ExcluirOSPorId(int id){
        String sql="DELETE FROM ordem_servico WHERE id=?";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,id);
            int afetadas=ps.executeUpdate();
            if(afetadas>0){
                System.out.println("OS Deletada com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao excluir o ordem_servico"+e.getMessage());
        }
    }

}
