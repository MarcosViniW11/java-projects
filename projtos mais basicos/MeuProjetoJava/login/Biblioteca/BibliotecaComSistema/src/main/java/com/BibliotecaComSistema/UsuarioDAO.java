package com.BibliotecaComSistema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public void CadastrarUsuario(Usuario usuario) {
        String sql="INSERT INTO usuario (nome,email,telefone) VALUES (?,?,?)";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setInt(3, usuario.getTelefone());
            ps.executeUpdate();

            try(ResultSet rs=ps.getGeneratedKeys()){
                if(rs.next()){
                    usuario.setId(rs.getInt(1));
                }
            }
            System.out.println("Usuario cadastrado com sucesso");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Usuario> ListarUsuarios() {
        String sql="SELECT * FROM usuario";
        List<Usuario> usuarios=new ArrayList<>();
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);ResultSet rs=ps.executeQuery();){
            while(rs.next()){
                Usuario usuario=new Usuario(rs.getString("nome"),rs.getString("email"),rs.getInt("telefone"));
                usuario.setId(rs.getInt("id"));
                usuarios.add(usuario);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return usuarios;
    }

    public Usuario BuscarUsuarioPorId(int id) {
        String sql="SELECT * FROM usuario WHERE id=?";
        Usuario usuario=null;
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                usuario=new Usuario(rs.getString("nome"),rs.getString("email"),rs.getInt("telefone"));
                usuario.setId(rs.getInt("id"));
                System.out.println("Usuario encontrado com sucesso");
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return usuario;
    }

    public void AlterarUsuarioPorId(Usuario usuario,int id) {
        String sql="UPDATE usuario SET nome=?,email=?,telefone=? WHERE id=?";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setInt(3, usuario.getTelefone());
            ps.setInt(4, id);

            int afetada=ps.executeUpdate();
            if(afetada>0){
                System.out.println("Usuario alterado com sucesso");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void ExcluirUsuarioPorId(int id) {
        String sql="DELETE FROM usuario WHERE id=?";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1, id);
            int afetada=ps.executeUpdate();
            if(afetada>0){
                System.out.println("Usuario deletado com sucesso");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
