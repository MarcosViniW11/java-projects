package com.Cadastro4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    public void CadastrarPessoa(Pessoa p){
        String sql = "INSERT INTO pessoas (nome,email) VALUES (?,?)";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
            ps.setString(1, p.getNome());
            ps.setString(2, p.getEmail());

            ps.executeUpdate();

            try(ResultSet rs=ps.getGeneratedKeys();){
                if(rs.next()){
                    p.setId(rs.getInt(1));
                }
            }
            System.out.println("Pessoa cadastrado com sucesso!\n"+p);


        } catch (Exception e) {
            System.out.println("Erro ao CadastrarPessoa\n"+e.getMessage());
        }

    }

    public List<Pessoa> listarPessoas(){
        String sql = "SELECT * FROM pessoas";
        List<Pessoa> listaPessoas = new ArrayList<>();
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);ResultSet rs=ps.executeQuery();){
            while(rs.next()){
                Pessoa p=new Pessoa(rs.getString("nome"),rs.getString("email"));
                p.setId(rs.getInt("id"));

                listaPessoas.add(p);

            }

        } catch (Exception e) {
            System.out.println("Erro ao listarPessoas\n"+e.getMessage());
        }
        return listaPessoas;

    }

    public Pessoa buscarPessoaPorId(int id){
        String sql = "SELECT * FROM pessoas WHERE id=?";

        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,id);

            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Pessoa p=new Pessoa(rs.getString("nome"),rs.getString("email"));
                p.setId(rs.getInt("id"));
                return p;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscarPessoaPorId\n"+e.getMessage());
        }
        return null;

    }

    public void AtualizarPessoaPorId(Pessoa p,int id){
        String sql = "UPDATE pessoas SET nome=?,email=? WHERE id=?";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setString(1, p.getNome());
            ps.setString(2, p.getEmail());
            ps.setInt(3, id);

            int Afetadas=ps.executeUpdate();

            if(Afetadas>0){
                System.out.println("Pessoa atualizado com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizarPessoaPorId\n"+e.getMessage());
        }

    }

    public void ExcluirPessoaPorId(int id){
        String sql = "DELETE FROM pessoas WHERE id=?";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1, id);

            int Afetadas=ps.executeUpdate();
            if(Afetadas>0){
                System.out.println("Pessoa removido com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao excluirPessoaPorId\n"+e.getMessage());
        }


    }

}
