package com.BibliotecaComSistema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public void CadastrarLivro(Livro livro){
        String sql="INSERT INTO livro (titulo,autor,anoPublicacao,quantidade) VALUES (?,?,?,?)";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setInt(3, livro.getAnoPublicacao());
            ps.setInt(4, livro.getQuantidade());

            ps.executeUpdate();

            try(ResultSet rs=ps.getGeneratedKeys()){
                if(rs.next()){
                    livro.setId(rs.getInt(1));
                }
            }
            System.out.println("Livro cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Livro> ListarLivros(){
        String sql="SELECT * FROM livro";
        List<Livro> listaLivros = new ArrayList<>();
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);ResultSet rs=ps.executeQuery();){
            while(rs.next()){
                Livro livro=new Livro(rs.getString("titulo"),rs.getString("autor"),rs.getInt("anoPublicacao"),rs.getInt("quantidade"));
                livro.setId(rs.getInt("id"));
                listaLivros.add(livro);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaLivros;
    }

    public Livro BuscarLivro(int id){
        String sql="SELECT * FROM livro WHERE id=?";
        Livro livro=null;
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                livro=new Livro(rs.getString("titulo"),rs.getString("autor"),rs.getInt("anoPublicacao"),rs.getInt("quantidade"));
                livro.setId(rs.getInt("id"));
                System.out.println("Livro buscado com sucesso!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return livro;
    }

    public void atualizarLivro(Livro livro,int id){
        String sql="UPDATE livro set titulo=?,autor=?,anoPublicacao=?,quantidade=? WHERE id=?";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setInt(3, livro.getAnoPublicacao());
            ps.setInt(4, livro.getQuantidade());
            ps.setInt(5, id);
            int afetadas=ps.executeUpdate();

            if(afetadas>0){
                System.out.println("Livro atualizado com sucesso!");
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void excluirLivro(int id){
        String sql="DELETE FROM livro WHERE id=?";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1, id);
            int afetadas=ps.executeUpdate();
            if(afetadas>0){
                System.out.println("Livro deletado com sucesso!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



}
