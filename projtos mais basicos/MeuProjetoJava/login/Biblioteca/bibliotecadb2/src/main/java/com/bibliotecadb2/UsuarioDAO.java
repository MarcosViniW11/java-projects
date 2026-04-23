package com.bibliotecadb2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public void CadastrarUsuario(Usuario usuario){
        String sql="INSERT INTO usuarios (nome,email,telefone) VALUES (?,?,?)";
        try(Connection conn= Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getTelefone());

            ps.executeUpdate();

            try(ResultSet rs=ps.getGeneratedKeys()){
                if(rs.next()){
                    usuario.setId(rs.getInt(1));
                }
            }
            System.out.println("Cadastrado com sucesso!!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public List<Usuario> ListarUsuarios(){
        String sql="SELECT * FROM usuarios";
        List<Usuario> listaUsuarios=new ArrayList<>();
        try(Connection conn= Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql);ResultSet rs=ps.executeQuery();){
            while(rs.next()){
                Usuario usuario=new Usuario(rs.getString("nome"),rs.getString("email"),rs.getString("telefone"));
                usuario.setId(rs.getInt("id"));
                listaUsuarios.add(usuario);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaUsuarios;

    }

    public void AtualizarUsuarioPorId(Usuario usuario,int id){
        String sql="UPDATE usuarios SET nome=?,email=?,telefone=? WHERE id=?";
        try(Connection conn= Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getTelefone());
            ps.setInt(4, id);

            int afetadas=ps.executeUpdate();
            if(afetadas>0){
                System.out.println("Usuario Atualizado com sucesso!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void ExcluirUsuarioPorId(int id){
        String sql="DELETE FROM usuarios WHERE id=?";
        try(Connection conn= Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1, id);
            int afetadas=ps.executeUpdate();
            if(afetadas>0){
                System.out.println("Usuario Excluido com sucesso!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public Usuario BuscarUsuarioPorId(int id){
        String sql="SELECT * FROM usuarios WHERE id=?";
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Usuario usuario=new Usuario(rs.getString("nome"),rs.getString("email"),rs.getString("telefone"));
                usuario.setId(rs.getInt("id"));
                return usuario;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
