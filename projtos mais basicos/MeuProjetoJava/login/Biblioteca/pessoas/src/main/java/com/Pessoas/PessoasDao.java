package com.Pessoas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PessoasDao {

    public void CadastrarPessoa(Pessoas pessoa){
        String sql = "INSERT INTO cadastro (nome,email) VALUES (?,?)";
        try(Connection conn=Conexao.getconexao(); PreparedStatement ps=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getEmail());

            ps.executeUpdate();

            try(ResultSet rs=ps.getGeneratedKeys()){
                if(rs.next()){
                    pessoa.setId(rs.getInt(1));
                }
            }
            System.out.println("Pessoa Cadastrado com sucesso!\n"+pessoa);


        }catch(Exception e){
            System.out.println("Erro ao CadastrarPessoa"+e.getMessage());
        }

    }

    public List<Pessoas> listarPessoas(){
        String sql = "SELECT * FROM cadastro";
        List<Pessoas> pessoas = new ArrayList<>();
        try(Connection conn=Conexao.getconexao(); PreparedStatement ps=conn.prepareStatement(sql); ResultSet rs=ps.executeQuery();){
            while(rs.next()){
                Pessoas pessoa=new Pessoas(rs.getString("nome"),rs.getString("email"));
                pessoa.setId(rs.getInt("id"));

                pessoas.add(pessoa);

                //System.out.println(pessoa);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listarPessoas"+e.getMessage());
        }

        return pessoas;

    }

    public Pessoas BuscarPessoa(int id){
        String sql = "SELECT * FROM cadastro WHERE id=?";

        try(Connection conn=Conexao.getconexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,id);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                Pessoas pessoa=new Pessoas(rs.getString("nome"),rs.getString("email"));
                pessoa.setId(rs.getInt("id"));
                return pessoa;
            }

        } catch (Exception e) {
            System.out.println("Erro ao listarPessoas "+e.getMessage());
        }
        return null;

    }

    public void AtualizarPessoa(Pessoas pessoa,int id){
        String sql = "UPDATE cadastro SET nome=?,email=? WHERE id=?";

        try(Connection conn=Conexao.getconexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getEmail());
            ps.setInt(3, id);

            int afetadas=ps.executeUpdate();

            if(afetadas>0){
                System.out.println("Pessoa Atualizado com sucesso!");
            }else {
                System.out.println("Erro ao AtualizarPessoa!");
            }

        }catch(Exception e){
            System.out.println("Erro ao atualizarPessoa"+e.getMessage());
        }
    }

    public void DeletarPessoa(int id){
        String sql = "DELETE FROM cadastro WHERE id=?";

        try(Connection conn=Conexao.getconexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1, id);

            int afetadas=ps.executeUpdate();
            if(afetadas>0){
                System.out.println("Pessoa Deletado com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao deletarPessoa"+e.getMessage());
        }
    }


}
