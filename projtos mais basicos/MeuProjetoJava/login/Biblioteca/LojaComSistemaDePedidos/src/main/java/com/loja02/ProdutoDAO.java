package com.loja02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void CadastrarProduto(Produto produto){
        String sql="INSERT INTO produto (nome,preco,estoque) VALUES (?,?,?)";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
            ps.setString(1,produto.getNome());
            ps.setInt(2,produto.getPreco());
            ps.setInt(3,produto.getQuantidade());
            ps.executeUpdate();

            try(ResultSet rs=ps.getGeneratedKeys()){
                if(rs.next()){
                    produto.setId(rs.getInt(1));
                }
            }
            System.out.println("Cadastro realizado com sucesso");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Produto> ListarProdutos(){
        String sql="SELECT * FROM produto";
        List<Produto> listaProdutos = new ArrayList<>();
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);ResultSet rs=ps.executeQuery();){
            while(rs.next()){
                Produto p=new Produto(rs.getString("nome"),rs.getInt("preco"),rs.getInt("estoque"));
                p.setId(rs.getInt("id"));
                listaProdutos.add(p);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaProdutos;
    }

    public void AtualizarProdutoPorId(Produto produto,int id){
        String sql="UPDATE produto SET nome=?,preco=?,estoque=? WHERE id=?";
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setString(1,produto.getNome());
            ps.setInt(2,produto.getPreco());
            ps.setInt(3,produto.getQuantidade());
            ps.setInt(4,id);
            int afetadas=ps.executeUpdate();
            if(afetadas>0){
                System.out.println("Atualizado com sucesso!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void ExcluirProduto(int id){
        String sql="DELETE FROM produto WHERE id=?";
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,id);
            int afetadas=ps.executeUpdate();
            if(afetadas>0){
                System.out.println("Excluido com sucesso!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Produto BuscarProdutoPorId(int id) {
        String sql = "SELECT * FROM produto WHERE id=?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Produto p = new Produto(
                        rs.getString("nome"),
                        rs.getInt("preco"),
                        rs.getInt("estoque")
                );
                p.setId(rs.getInt("id"));
                return p; // retorna objeto completo
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
