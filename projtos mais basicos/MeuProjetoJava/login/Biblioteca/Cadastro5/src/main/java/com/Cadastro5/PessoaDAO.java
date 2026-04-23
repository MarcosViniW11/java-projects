package com.Cadastro5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    public void CadastrarPessoa (Pessoa pessoa){
        String sql="INSERT INTO pessoas (nome,email) VALUES (?,?)";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
            ps.setString(1,pessoa.getNome());
            ps.setString(2,pessoa.getEmail());

            ps.executeUpdate();

            try(ResultSet rs=ps.getGeneratedKeys()){
                if(rs.next()){
                    pessoa.setId(rs.getInt(1));
                }
            }
            System.out.println("Pessoa Cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao CadastrarPessoa\n"+e.getMessage());
        }

    }

    public List<Pessoa> listarPessoas(){
        String sql="SELECT * FROM pessoas";
        List<Pessoa> listaPessoas=new ArrayList<>();

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

    public void AtualizarPessoaPorId (Pessoa pessoa,int id){
        String sql="UPDATE pessoas SET nome=?,email=? WHERE id=?";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setString(1,pessoa.getNome());
            ps.setString(2,pessoa.getEmail());
            ps.setInt(3,id);

            int Afetadas=ps.executeUpdate();
            if(Afetadas>0){
                System.out.println("Pessoa Atualizado com sucesso!");
            }else{
                System.out.println("ERROR AO ATUALIZAR Pessoa!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizarPessoaPorId\n"+e.getMessage());
        }

    }

    public void ExcluirPessoaPorId(int id){
        String sql="DELETE FROM pessoas WHERE id=?";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,id);
            int Afetadas=ps.executeUpdate();
            if(Afetadas>0){
                System.out.println("Pessoa Deletado com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao excluirPessoaPorId\n"+e.getMessage());
        }
    }

}
