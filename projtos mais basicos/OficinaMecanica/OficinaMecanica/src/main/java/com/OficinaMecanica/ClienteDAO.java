package com.OficinaMecanica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void Cadastrar(Cliente c){
        String sql="INSERT INTO cliente (nome,telefone,email,endereco,statos) VALUES (?,?,?,?,?)";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
            ps.setString(1, c.getNome());
            ps.setString(2, c.getTelefone());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getEndereco());
            ps.setString(5, c.getStatus());
            ps.executeUpdate();

            try(ResultSet rs=ps.getGeneratedKeys()){
                if(rs.next()){
                    c.setId(rs.getInt(1));
                }
            }
            System.out.println("Cliente cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao Cadastrar: "+e.getMessage());
        }
    }

    public List<Cliente> ListarClientes(){
        String sql="SELECT * FROM cliente";
        List<Cliente> listaClientes=new ArrayList<>();
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);ResultSet rs=ps.executeQuery();){
            while(rs.next()){
                Cliente cliente=new Cliente(rs.getString("nome"),rs.getString("telefone"),rs.getString("email"),rs.getString("endereco"),rs.getString("statos"));
                cliente.setId(rs.getInt("id"));
                listaClientes.add(cliente);
            }

        } catch (Exception e) {
            System.out.println("Erro ao ListarClientes: "+e.getMessage());
        }
        return listaClientes;
    }

    public void excluirCliente(int id){
        String sql="DELETE FROM cliente WHERE id=?";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,id);
            int afetadas=ps.executeUpdate();
            if(afetadas>0){
                System.out.println("Cliente excluido com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluirCliente: "+e.getMessage());
        }
    }

    public void atualizarClientePorId(Cliente c,int id){
        String sql="UPDATE cliente SET nome=?,telefone=?,email=?,endereco=?,statos=? WHERE id=?";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setString(1, c.getNome());
            ps.setString(2, c.getTelefone());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getEndereco());
            ps.setString(5, c.getStatus());
            ps.setInt(6,id);
            int afetadas=ps.executeUpdate();
            if(afetadas>0){
                System.out.println("Cliente atualizado com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizarClientePorId: "+e.getMessage());
        }
    }

    public List<Cliente> BuscarClientePorNome(String nome){
        String sql="SELECT * FROM cliente WHERE nome=?";
        List<Cliente> listaClientes=new ArrayList<>();
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setString(1,nome);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Cliente cliente=new Cliente(rs.getString("nome"),rs.getString("telefone"),rs.getString("email"),rs.getString("endereco"),rs.getString("statos"));
                cliente.setId(rs.getInt("id"));
                listaClientes.add(cliente);
            }

        } catch (Exception e) {
            System.out.println("Erro ao BuscarClientePorNome: "+e.getMessage());
        }
        return listaClientes;
    }

    public Cliente BuscarClientePorId(int id){
        String sql="SELECT * FROM cliente WHERE id=?";
        Cliente cliente=new Cliente();
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setStatus(rs.getString("statos"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao BuscarClientePorId: "+e.getMessage());
        }
        return cliente;
    }


}
