package com.loja;

import java.sql.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void inserir(Produto produto){
        String sql = "INSERT INTO produtos (nome, preco, quantidade) VALUES (?,?,?)";
        try(Connection conn = Conexao.getConexao();
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, produto.getNome());
            ps.setDouble(2,produto.getPreco());
            ps.setInt(3, produto.getQuantidade());

            ps.executeUpdate();

            try(ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    produto.setId(rs.getInt(1));
                }
            }
            System.out.println("Produto inserido com sucesso" + produto);

        }catch (SQLException e){
            System.out.println("Erro ao inserir" + e.getMessage());
        }

    }

    public List<Produto> listarTodos() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT id, nome, preco, quantidade FROM produtos";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto(rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade"));
                p.setId(rs.getInt("id"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }

        return lista;
    }

    public Produto buscarPorId(int id) {
        String sql = "SELECT id, nome, preco, quantidade FROM produtos WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Produto p = new Produto(rs.getString("nome"),
                            rs.getDouble("preco"),
                            rs.getInt("quantidade"));
                    p.setId(rs.getInt("id"));
                    return p;
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar produto: " + e.getMessage());
        }
        return null;
    }

    public void atualizar(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, preco = ?, quantidade = ? WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setInt(3, produto.getQuantidade());
            ps.setInt(4, produto.getId());

            int afetadas = ps.executeUpdate();
            if (afetadas > 0) {
                System.out.println("✅ Produto atualizado: " + produto);
            } else {
                System.out.println("Nenhum produto encontrado com id " + produto.getId());
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int afetadas = ps.executeUpdate();
            if (afetadas > 0) {
                System.out.println("✅ Produto removido (id=" + id + ")");
            } else {
                System.out.println("Nenhum produto encontrado com id " + id);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao deletar produto: " + e.getMessage());
        }
    }

}
