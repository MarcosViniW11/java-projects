package com.SistemaDeAcademia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public void CadastrarAluno(Aluno aluno){
        String sql="INSERT INTO aluno (nome,idade,plano,statu) VALUES (?,?,?,?)";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
            ps.setString(1,aluno.getNome());
            ps.setInt(2,aluno.getIdade());
            ps.setString(3,aluno.getPlano());
            ps.setString(4,aluno.getStatus());

            ps.executeUpdate();

            try(ResultSet rs=ps.getGeneratedKeys()){
                if(rs.next()){
                    aluno.setId(rs.getInt(1));
                }
            }
            System.out.println("Aluno Cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public List<Aluno> ListarAlunos(){
        String sql="SELECT * FROM aluno";
        List<Aluno> listaAlunos=new ArrayList<>();
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);ResultSet rs=ps.executeQuery();){
            while(rs.next()){
                Aluno aluno=new Aluno(rs.getString("nome"),rs.getInt("idade"),rs.getString("plano"),rs.getString("statu"));
                aluno.setId(rs.getInt("id"));
                listaAlunos.add(aluno);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaAlunos;
    }

    public void atualizarAluno(Aluno aluno,int id){
        String sql="UPDATE aluno SET nome=?,idade=?,plano=?,statu=? WHERE id=?";
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setString(1,aluno.getNome());
            ps.setInt(2,aluno.getIdade());
            ps.setString(3,aluno.getPlano());
            ps.setString(4,aluno.getStatus());
            ps.setInt(5,id);

            int afetadas=ps.executeUpdate();

            if(afetadas>0){
                System.out.println("Aluno atualizado com sucesso!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void excluirAluno(int id){
        String sql="DELETE FROM aluno WHERE id=?";
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,id);
            int afetadas=ps.executeUpdate();
            if(afetadas>0){
                System.out.println("Aluno Inativado/Excluido com sucesso!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
