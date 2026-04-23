package com.Blioteca;

import java.sql.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaDAO {

    public void CadastrarLivro(Livro livro){
        String sql="INSERT INTO livros (titulo,autor) VALUES (?,?)";
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());

            ps.executeUpdate();

            try (ResultSet rs=ps.getGeneratedKeys()){
                if(rs.next()){
                    livro.setId(rs.getInt(1));
                }

            }
            System.out.println("Livro cadastrado com sucesso! \n"+livro);

        }catch (Exception e){
            System.out.println("Erro ao Cadastrar o Livro: \n"+ e.getMessage());
        }

    }

    public List<Livro> ListarLivros(){
        List<Livro> livros = new ArrayList<>();
        String sql="SELECT * FROM livros";
        try(Connection conn = Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql);ResultSet rs=ps.executeQuery();){
            while(rs.next()){
                Livro livro = new Livro(rs.getString("titulo"),rs.getString("autor"));
                livro.setId(rs.getInt("id"));

                livros.add(livro);
                System.out.println(livro);

            }



        }catch (Exception e){
            System.out.println("Erro ao Listar Livros: \n"+ e.getMessage());
        }

        return livros;

    }

    public Livro BuscarLivro(int id){
        String sql="SELECT * FROM livros WHERE id=?";
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,id);

            try(ResultSet rs=ps.executeQuery();){
                if(rs.next()){
                    Livro livro = new Livro(rs.getString("titulo"),rs.getString("autor"));
                    livro.setId(rs.getInt("id"));
                    return livro;
                }

            }

        }catch (Exception e){
            System.out.println("Erro ao Buscar Livro: \n"+ e.getMessage());
        }

        return null;
    }

    public void DeletarLivro(int id){
        String sql="DELETE FROM livros WHERE id=?";
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,id);
            int afetada=ps.executeUpdate();
            if(afetada>0){
                System.out.println("Livro deletado com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao deletar Livro: \n"+ e.getMessage());
        }
    }

}
