package com.Cadastro2;

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
            System.out.println("Pessoa Cadastrado com sucesso!\n"+pessoa);


        }catch(Exception e){
            System.out.println("Erro ao CadastrarPessoa\n"+e.getMessage());
        }

    }

    public List<Pessoa> listarPessoas(){
        String sql="SELECT * FROM pessoas";
        List<Pessoa> listaPessoas=new ArrayList<>();
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);ResultSet rs=ps.executeQuery();){
            while(rs.next()){
                Pessoa p=new Pessoa(rs.getString(1),rs.getString(2));
                p.setId(rs.getInt("id"));
                listaPessoas.add(p);

            }

        } catch (Exception e) {
            System.out.println("Erro ao listarPessoas\n"+e.getMessage());
        }

        return listaPessoas;


    }

    public Pessoa buscarPessoa(int id){
        String sql="SELECT * FROM pessoas WHERE id=?";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,id);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                Pessoa p=new Pessoa(rs.getString(1),rs.getString(2));
                p.setId(rs.getInt("id"));
                return p;
            }else {
                System.out.println("pessoa nao encontrada com este id");
            }

        }catch (Exception e){
            System.out.println("Erro ao buscarPessoa\n"+e.getMessage());
        }

        return null;

    }

    public void AtualizarPessoaPorId (Pessoa pessoa,int id){
        String sql="UPDATE pessoas SET nome=?,email=? WHERE id=?";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setString(1,pessoa.getNome());
            ps.setString(2,pessoa.getEmail());
            ps.setInt(3,id);

            int Afetado=ps.executeUpdate();

            if(Afetado>0){
                System.out.println("Pessoa atualizado com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizarPessoaPorId\n"+e.getMessage());
        }

    }

    public void DeletarPessoaPorId(int id){
        String sql="DELETE FROM pessoas WHERE id=?";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,id);

            int Afetado=ps.executeUpdate();

            if(Afetado>0){
                System.out.println("Pessoa Deletada com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao deletarPessoaPorId\n"+e.getMessage());
        }


    }

}
