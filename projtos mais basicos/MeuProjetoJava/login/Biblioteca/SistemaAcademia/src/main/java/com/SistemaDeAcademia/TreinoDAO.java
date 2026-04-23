package com.SistemaDeAcademia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TreinoDAO {

    public void CadastrarTreino(Treino t){
        String sql="INSERT INTO treino (idAluno,descricao,inicio,fim) VALUES (?,?,?,?)";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
            ps.setInt(1, t.getIdAluno());
            ps.setString(2, t.getDescricao());
            ps.setDate(3, new java.sql.Date(t.getDataInicio().getTime()));
            ps.setDate(4, new java.sql.Date(t.getDataFim().getTime()));

            ps.executeUpdate();

            try(ResultSet rs=ps.getGeneratedKeys()){
                if(rs.next()){
                    t.setId(rs.getInt(1));
                }
            }
            System.out.println("Treino Cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public List<Treino> ListarTreinoDeUmAluno(int idAluno){
        String sql="SELECT * FROM treino WHERE idAluno=?";
        List<Treino> listaTreino = new ArrayList<>();
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1, idAluno);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Treino treino=new Treino(rs.getInt("idAluno"),rs.getString("descricao"),rs.getDate("inicio"),rs.getDate("fim"));
                treino.setId(rs.getInt("id"));
                listaTreino.add(treino);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaTreino;
    }

    public void atualizarTreino(Treino t,int id){
        String sql="UPDATE treino SET idAluno=?,descricao=?,inicio=?,fim=? WHERE id=?";
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1, t.getIdAluno());
            ps.setString(2, t.getDescricao());
            ps.setDate(3, new java.sql.Date(t.getDataInicio().getTime()));
            ps.setDate(4, new java.sql.Date(t.getDataFim().getTime()));
            ps.setInt(5, id);

            int afetadas=ps.executeUpdate();
            if(afetadas>0){
                System.out.println("Treino atualizado com sucesso!");
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void excluirTreino(int id){
        String sql="DELETE FROM treino WHERE id=?";
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1, id);
            int afetadas=ps.executeUpdate();
            if(afetadas>0){
                System.out.println("Treino removido com sucesso!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
