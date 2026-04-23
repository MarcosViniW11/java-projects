package com.OficinaMecanica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {

    public void CadastrarServico(Servico servico){
        String sql="INSERT INTO servico (nome,observacao,valor) VALUES (?,?,?)";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
            ps.setString(1, servico.getNome());
            ps.setString(2, servico.getObservacao());
            ps.setDouble(3, servico.getValor());
            ps.executeUpdate();

            try(ResultSet rs=ps.getGeneratedKeys()){
                if(rs.next()){
                    servico.setId(rs.getInt(1));
                }
            }
            System.out.println("Servico Cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao CadastrarServico"+e.getMessage());
        }

    }

    public List<Servico> ListarServicos(){
        String sql="SELECT * FROM servico";
        List<Servico> listaServicos=new ArrayList<>();
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql);ResultSet rs=ps.executeQuery();){
            while(rs.next()){
                Servico servico=new Servico(rs.getString("nome"),rs.getString("observacao"),rs.getDouble("valor"));
                servico.setId(rs.getInt("id"));
                listaServicos.add(servico);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listarServicos"+e.getMessage());
        }
        return listaServicos;
    }

    public Servico BuscarServicoPorId(int id){
        String sql="SELECT * FROM servico WHERE id=?";
        Servico servico=new Servico();
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                servico.setId(rs.getInt("id"));
                servico.setNome(rs.getString("nome"));
                servico.setDescricao(rs.getString("observacao"));
                servico.setPreco(rs.getDouble("valor"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscarServicoPorId"+e.getMessage());
        }
        return servico;
    }

    public void AtualizarServicoPorId(Servico servico,int id){
        String sql="UPDATE servico SET nome=?,observacao=?,valor=? WHERE id=?";
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setString(1, servico.getNome());
            ps.setString(2, servico.getObservacao());
            ps.setDouble(3, servico.getValor());
            ps.setInt(4, id);
            int afetadas=ps.executeUpdate();
            if(afetadas>0){
                System.out.println("Servico atualizado com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizarServicoPorId"+e.getMessage());
        }

    }

    public void ExcluirServicoPorId(int id){
        String sql="DELETE FROM servico WHERE id=?";
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1, id);
            int afetadas=ps.executeUpdate();
            if(afetadas>0){
                System.out.println("Servico removido com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluirServicoPorId"+e.getMessage());
        }
    }


}
