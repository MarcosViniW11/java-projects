package com.SistemaDeAcademia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExercicioDAO {

    public void CadastrarExercicio(Exercicio exercicio){
        String sql="INSERT INTO exercicio (idTreino,nome,series,repetcoes,observacao) VALUES (?,?,?,?,?)";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
            ps.setInt(1,exercicio.getIdTreino());
            ps.setString(2,exercicio.getNome());
            ps.setInt(3,exercicio.getSeries());
            ps.setInt(4,exercicio.getRepetcoes());
            ps.setString(5,exercicio.getObservacao());
            ps.executeUpdate();

            try(ResultSet rs=ps.getGeneratedKeys()){
                if(rs.next()){
                    exercicio.setId(rs.getInt(1));
                }
            }
            System.out.println("Exercicio cadastrado com sucesso");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Exercicio> ListarExercicio(int idTreino){
        String sql="SELECT * FROM exercicio where idTreino=?";
        List<Exercicio> listaExercicios=new ArrayList<>();
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,idTreino);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Exercicio exercicio=new Exercicio(rs.getInt("idTreino"),rs.getString("nome"),rs.getInt("series"),rs.getInt("repetcoes"),rs.getString("observacao"));
                exercicio.setId(rs.getInt("id"));
                listaExercicios.add(exercicio);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaExercicios;
    }

    public void AtualizarExercicio(Exercicio exercicio,int id){
        String sql="UPDATE exercicio SET idTreino=?,nome=?,series=?,repetcoes=?,observacao=? WHERE id=?";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,exercicio.getIdTreino());
            ps.setString(2,exercicio.getNome());
            ps.setInt(3,exercicio.getSeries());
            ps.setInt(4,exercicio.getRepetcoes());
            ps.setString(5,exercicio.getObservacao());
            ps.setInt(6,id);

            int afetada=ps.executeUpdate();
            if(afetada>0){
                System.out.println("Exercicio atualizado com sucesso");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void ExcluirExercicio(int id){
        String sql="DELETE FROM exercicio WHERE id=?";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,id);
            int afetada=ps.executeUpdate();
            if(afetada>0){
                System.out.println("Exercicio deletado com sucesso");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
