package com.ProjetoBibliotecaFinal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public void CadastrarLivro(Livro livro){
        String sql="INSERT INTO livro (titulo,autor,categoria,statos) VALUES (?,?,?,?)";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setString(3, livro.getCategoria());
            ps.setString(4, livro.getStatos());
            ps.executeUpdate();

            try(ResultSet rs=ps.getGeneratedKeys()){
                if(rs.next()){
                    livro.setId(rs.getInt(1));
                }
            }
            System.out.println("Livro cadastrado com sucesso");

        } catch (Exception e) {
            System.out.println("Erro ao CadastrarLivro"+e.getMessage());
        }
    }

    public List<Livro> ListarLivro(){
        String sql="SELECT * FROM livro";
        List<Livro> listaLivro = new ArrayList<>();
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);ResultSet rs=ps.executeQuery();){
            while(rs.next()){
                Livro livro=new Livro(rs.getString("titulo"),rs.getString("autor"),rs.getString("categoria"),rs.getString("statos"));
                livro.setId(rs.getInt("id"));
                listaLivro.add(livro);
            }

        } catch (Exception e) {
            System.out.println("Erro ao ListarLivro"+e.getMessage());
        }
        return listaLivro;
    }

    public void atualizarLivro(Livro livro,int id){
        String sql="UPDATE livro SET titulo=?,autor=?,categoria=?,statos=? WHERE id=?";
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setString(3, livro.getCategoria());
            ps.setString(4, livro.getStatos());
            ps.setInt(5, id);

            int afetadas=ps.executeUpdate();
            if(afetadas>0){
                System.out.println("Livro atualizado com sucesso!");
            }


        } catch (Exception e) {
            System.out.println("Erro ao atualizarLivro"+e.getMessage());
        }
    }

    public void excluirLivro(int id){
        String sql="DELETE FROM livro WHERE id=?";
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1, id);
            int afetadas=ps.executeUpdate();
            if(afetadas>0){
                System.out.println("Livro deletado com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao excluirLivro"+e.getMessage());
        }
    }

    public void AtualizarStatusLivro(int idLivro,String status){
        String sql="UPDATE livro SET statos=? WHERE id=?";
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setString(1, status);
            ps.setInt(2, idLivro);
            int afetadas=ps.executeUpdate();
            if(afetadas>0){
                System.out.println("Status do livro atualizado com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao AtualizarStatusLivro"+e.getMessage());
        }
    }

    public List<Livro> ListarLivroDisponiveis(){
        String sql="SELECT * FROM livro WHERE statos = 'Disponivel'";
        List<Livro> listaLivroDisponiveis = new ArrayList<>();
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql);ResultSet rs=ps.executeQuery();){
            while(rs.next()){
                Livro livro=new Livro(rs.getString("titulo"),rs.getString("autor"),rs.getString("categoria"),rs.getString("statos"));
                livro.setId(rs.getInt("id"));
                listaLivroDisponiveis.add(livro);
            }


        } catch (Exception e) {
            System.out.println("Erro ao listarLivroDisponiveis"+e.getMessage());
        }
        return listaLivroDisponiveis;
    }

}
