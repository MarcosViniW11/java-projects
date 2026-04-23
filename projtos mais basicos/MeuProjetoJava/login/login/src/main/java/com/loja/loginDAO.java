package com.loja;

import java.sql.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class loginDAO {

    public void CadastrarPessoa (Pessoa pessoa){
        String sql="INSERT INTO pessoa (email,nome) VALUES (?,?)";
        try(Connection conn= Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, pessoa.getEmail());
            ps.setString(2, pessoa.getNome());

            ps.executeUpdate();

            try(ResultSet rs= ps.getGeneratedKeys()){
                if (rs.next()){
                    pessoa.setId(rs.getInt(1));
                }
            }
            System.out.println("Cadastrado com sucesso\n"+pessoa);

        }catch (SQLException e){
            System.out.println("Erro ao Cadastrar Pessoa\n"+e.getMessage());

        }


    }

    public List<Pessoa> ListarPessoas(){
        List <Pessoa> pessoas=new ArrayList<>();
        String sql="SELECT id,email,nome FROM pessoa";

        try(Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql);ResultSet rs=ps.executeQuery()){

            while(rs.next()){
                Pessoa p=new Pessoa(rs.getString("email"),rs.getString("nome"));
                p.setId(rs.getInt("id"));
                pessoas.add(p);
            }


        }catch (SQLException e){
            System.out.println("Erro ao Listar Pessoas\n"+e.getMessage());
        }

        return pessoas;


    }

    public Pessoa BuscarPorId(int id){
        String sql="SELECT * FROM pessoa WHERE id=?";

        try(Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,id);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Pessoa p=new Pessoa(rs.getString("email"),rs.getString("nome"));
                    p.setId(rs.getInt("id"));
                    return p;
                }

            }

        }catch (Exception e){
            System.out.println("Erro ao Buscar Pessoa\n"+e.getMessage());
        }
        return null;

    }

    public void atualizarPessoa (Pessoa pessoa){
        String sql="UPDATE pessoa SET email=?,nome=? WHERE id=?";

        try(Connection conn= Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, pessoa.getEmail());
            ps.setString(2, pessoa.getNome());
            ps.setInt(3, pessoa.getId());

            int afetadas=ps.executeUpdate();

            if(afetadas>0){
                System.out.println("Cadastro Atualizado com sucesso\n"+pessoa);
            }else{
                System.out.println("Erro ao atualizar Cadastro\n");
            }

        }catch (Exception e){
            System.out.println("Erro ao atualizar Pessoa\n"+e.getMessage());
        }

    }

    public void deletarPessoa(int id){
        String sql="DELETE FROM pessoa WHERE id=?";

        try(Connection conn= Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, id);
            int afetadas=ps.executeUpdate();
            if(afetadas>0){
                System.out.println("Pessoa Deletada com sucesso com o id \n"+id);

            }else{
                System.out.println("Erro ao deletar Pessoa\n");
            }

        }catch (Exception e){
            System.out.println("Erro ao deletar Pessoa\n"+e.getMessage());
        }

    }


}
