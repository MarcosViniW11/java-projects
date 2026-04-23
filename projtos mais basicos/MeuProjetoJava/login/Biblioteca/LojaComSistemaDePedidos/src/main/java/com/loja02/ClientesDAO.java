package com.loja02;

import org.w3c.dom.ls.LSOutput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientesDAO {

    public void CadastrarCliente(Clientes c){
        String sql="insert into clientes (nome,email,tel) values (?,?,?)";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
            ps.setString(1,c.getNome());
            ps.setString(2,c.getEmail());
            ps.setString(3,c.getTelefone());
            ps.executeUpdate();

            try(ResultSet rs=ps.getGeneratedKeys()){
                if(rs.next()){
                    c.setId(rs.getInt(1));
                }
            }
            System.out.println("Cadastro realizado com sucesso");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Clientes> listarClientes(){
        String sql="select * from clientes";
        List<Clientes> listaClientes=new ArrayList<>();
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);ResultSet rs=ps.executeQuery();){
            while(rs.next()){
                Clientes c=new Clientes(rs.getString("nome"),rs.getString("email"),rs.getString("tel"));
                c.setId(rs.getInt("id"));
                listaClientes.add(c);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaClientes;
    }

    public void AtualizarClientePorId(Clientes c,int id){
        String sql="UPDATE clientes SET nome=?,email=?,tel=? where id=?";
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setString(1,c.getNome());
            ps.setString(2,c.getEmail());
            ps.setString(3,c.getTelefone());
            ps.setInt(4,id);

            int afetado=ps.executeUpdate();
            if(afetado>0){
                System.out.println("Cliente atualizado com sucesso!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void ExcluirCliente(int id){
        String sql="delete from clientes where id=?";
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,id);
            int afetado=ps.executeUpdate();
            if(afetado>0){
                System.out.println("Cliente deletado com sucesso!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Clientes buscarClientePorId(int id) {
        String sql = "SELECT * FROM clientes WHERE id=?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Clientes c = new Clientes(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("tel")
                );
                c.setId(rs.getInt("id"));
                return c; // retorna objeto completo
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }



}
