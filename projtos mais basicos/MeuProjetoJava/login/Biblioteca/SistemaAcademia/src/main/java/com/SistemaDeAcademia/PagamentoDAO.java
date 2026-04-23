package com.SistemaDeAcademia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {

    public void CadastrarPagamento(Pagamento pagamento){
        String sql="INSERT INTO pagamento (idAluno,valor,data_pagamento,data_vencimento,status) VALUES (?,?,?,?,?)";
        try(Connection conn=Conexao.getConexao();
            PreparedStatement ps=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, pagamento.getIdAluno());
            ps.setDouble(2, pagamento.getValor());
            ps.setDate(3, new java.sql.Date(pagamento.getDataPagamento().getTime()));
            ps.setDate(4, new java.sql.Date(pagamento.getDataVencimento().getTime()));
            ps.setString(5, pagamento.getStatus());
            ps.executeUpdate();

            try(ResultSet rs=ps.getGeneratedKeys()){
                if(rs.next()){
                    pagamento.setId(rs.getInt(1));
                }
            }
            System.out.println("✅ Pagamento cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar pagamento: " + e.getMessage());
        }
    }

    public List<Pagamento> ListarPagamentos(){
        String sql="SELECT * FROM pagamento";
        List<Pagamento> pagamentos=new ArrayList<>();
        try(Connection conn=Conexao.getConexao();
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery()){

            while(rs.next()){
                Pagamento pagamento = new Pagamento(
                        rs.getInt("idAluno"),
                        rs.getDouble("valor"),
                        rs.getDate("data_pagamento"),
                        rs.getDate("data_vencimento"),
                        rs.getString("status")
                );
                pagamento.setId(rs.getInt("id"));
                pagamentos.add(pagamento);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar pagamentos: " + e.getMessage());
        }
        return pagamentos;
    }

    public List<Pagamento> ListarPagamentoPorAluno(int idAluno){
        String sql="SELECT * FROM pagamento WHERE idAluno=?";
        List<Pagamento> pagamentos=new ArrayList<>();
        try(Connection conn=Conexao.getConexao();
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setInt(1, idAluno);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){
                Pagamento pagamento = new Pagamento(
                        rs.getInt("idAluno"),
                        rs.getDouble("valor"),
                        rs.getDate("data_pagamento"),
                        rs.getDate("data_vencimento"),
                        rs.getString("status")
                );
                pagamento.setId(rs.getInt("id"));
                pagamentos.add(pagamento);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar pagamento do aluno: " + e.getMessage());
        }
        return pagamentos;
    }

    public void AtualizarStatusPagamentosVencidos(){
        String sql="UPDATE pagamento SET status='Vencido' WHERE data_vencimento < CURDATE() AND status != 'Pago'";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql)){
            int afetadas = ps.executeUpdate();
            if(afetadas > 0){
                System.out.println("⚠️ Pagamentos vencidos atualizados!");
            }
        } catch (Exception e){
            System.out.println("Erro ao atualizar vencimentos: " + e.getMessage());
        }
    }

    public void AtualizarPagamento(Pagamento pagamento, int id){
        String sql="UPDATE pagamento SET idAluno=?,valor=?,data_pagamento=?,data_vencimento=?,status=? WHERE id=?";
        try(Connection conn=Conexao.getConexao();
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setInt(1, pagamento.getIdAluno());
            ps.setDouble(2, pagamento.getValor());
            ps.setDate(3, new java.sql.Date(pagamento.getDataPagamento().getTime()));
            ps.setDate(4, new java.sql.Date(pagamento.getDataVencimento().getTime()));
            ps.setString(5, pagamento.getStatus());
            ps.setInt(6, id);

            int afetadas = ps.executeUpdate();
            if(afetadas > 0){
                System.out.println("✅ Pagamento atualizado com sucesso!");
            }

        } catch (Exception e){
            System.out.println("Erro ao atualizar pagamento: " + e.getMessage());
        }
    }

    public void ExcluirPagamento(int id){
        String sql="DELETE FROM pagamento WHERE id=?";
        try(Connection conn=Conexao.getConexao();
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setInt(1, id);
            int afetadas = ps.executeUpdate();
            if(afetadas > 0){
                System.out.println("🗑️ Pagamento excluído com sucesso!");
            }

        } catch (Exception e){
            System.out.println("Erro ao excluir pagamento: " + e.getMessage());
        }
    }
}
