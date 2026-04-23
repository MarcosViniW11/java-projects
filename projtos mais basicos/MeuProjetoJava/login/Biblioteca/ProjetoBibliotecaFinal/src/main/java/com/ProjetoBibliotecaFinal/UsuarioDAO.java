package com.ProjetoBibliotecaFinal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Cadastrar usuário
    public boolean CadastrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, telefone, tipo, qtbEmprestimosAtivos, limiteEmprestimos, statos) VALUES (?, ?, ?, ?, ?, ?, ?)";

        int limite = 3; // padrão para alunos
        if (usuario.getTipo().equalsIgnoreCase("Professor")) {
            limite = 5;
        }

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getTelefone());
            ps.setString(4, usuario.getTipo());
            ps.setInt(5, usuario.getQtbEmprestimosAtivos());
            ps.setInt(6, limite);
            ps.setString(7, usuario.getStatos());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId(rs.getInt(1));
                }
            }

            System.out.println("Usuário cadastrado com sucesso!");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
            return false;
        }
    }

    // Listar todos os usuários
    public List<Usuario> listarUsuarios() {
        String sql = "SELECT * FROM usuario";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("tipo"),
                        rs.getInt("qtbEmprestimosAtivos"),
                        rs.getInt("limiteEmprestimos"),
                        rs.getString("statos")
                );
                usuario.setId(rs.getInt("id"));
                usuarios.add(usuario);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    // Atualizar usuário
    public void AtualizarUsuario(Usuario usuario, int id) {
        String sql = "UPDATE usuario SET nome=?, email=?, telefone=?, tipo=?, qtbEmprestimosAtivos=?, limiteEmprestimos=?, statos=? WHERE id=?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getTelefone());
            ps.setString(4, usuario.getTipo());
            ps.setInt(5, usuario.getQtbEmprestimosAtivos());
            ps.setInt(6, usuario.getLimiteEmprestimos());
            ps.setString(7, usuario.getStatos());
            ps.setInt(8, id);

            int afetadas = ps.executeUpdate();
            if (afetadas > 0) {
                System.out.println("Usuário atualizado com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    // Excluir usuário
    public void ExcluirUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE id=?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int afetadas = ps.executeUpdate();
            if (afetadas > 0) {
                System.out.println("Usuário deletado com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao excluir usuário: " + e.getMessage());
        }
    }

    // Atualizar quantidade de empréstimos ativos
    public void atualizarQuantidadeEmprestimos(int idUsuario, int qtbEmprestimosAtivos) {
        String sql = "UPDATE usuario SET qtbEmprestimosAtivos=? WHERE id=?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, qtbEmprestimosAtivos);
            ps.setInt(2, idUsuario);

            int afetadas = ps.executeUpdate();
            if (afetadas > 0) {
                System.out.println("Quantidade de empréstimos atualizada com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizar quantidade de empréstimos: " + e.getMessage());
        }
    }

    // Listar usuários com pelo menos um empréstimo ativo
    public List<Usuario> listarUsuariosComEmprestimoAtivo() {
        String sql = "SELECT * FROM usuario WHERE qtbEmprestimosAtivos>=1";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("tipo"),
                        rs.getInt("qtbEmprestimosAtivos"),
                        rs.getInt("limiteEmprestimos"),
                        rs.getString("statos")
                );
                usuario.setId(rs.getInt("id"));
                usuarios.add(usuario);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar usuários com empréstimo ativo: " + e.getMessage());
        }
        return usuarios;
    }
}
