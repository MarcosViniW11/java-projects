package com.bibliotecadb2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class livroDAO {

    public void CadastrarLivro(Livro livro){
        String sql="INSERT INTO livros (titulo,autor,ano_publicacao,genero,quantidade) VALUES (?,?,?,?,?)";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setInt(3, livro.getAno_publicacao());
            ps.setString(4, livro.getGenero());
            ps.setInt(5, livro.getQuantidade());

            ps.executeUpdate();

            try(ResultSet rs=ps.getGeneratedKeys()){
                if(rs.next()){
                    livro.setId(rs.getInt(1));
                }
            }
            System.out.println("Livro cadastrado com sucesso:  "+livro);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public List<Livro> ListarLivros(){
        String sql="SELECT * FROM livros";
        List<Livro> listaLivros=new ArrayList<>();

        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);ResultSet rs=ps.executeQuery();){
            while(rs.next()){
                Livro livro=new Livro(rs.getString("titulo"),rs.getString("autor"),rs.getInt("ano_publicacao"),rs.getString("genero"),rs.getInt("quantidade"));
                livro.setId(rs.getInt("id"));
                listaLivros.add(livro);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaLivros;

    }

    public void AtualizarLivroPorId(Livro livro,int id){
        String sql="UPDATE livros SET titulo=?,autor=?,ano_publicacao=?,genero=?,quantidade=? WHERE id=?";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setInt(3, livro.getAno_publicacao());
            ps.setString(4, livro.getGenero());
            ps.setInt(5, livro.getQuantidade());
            ps.setInt(6, id);

            int afetadas=ps.executeUpdate();
            if(afetadas>0){
                System.out.println("Livro atualizado com sucesso");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void ExcluirLivroPorId(int id){
        String sql="DELETE FROM livros WHERE id=?";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1, id);
            int afetadas=ps.executeUpdate();
            if(afetadas>0){
                System.out.println("Livro deletado com sucesso");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
