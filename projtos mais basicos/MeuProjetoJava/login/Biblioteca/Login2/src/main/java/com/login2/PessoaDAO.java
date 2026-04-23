package com.login2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    public void CadastrarPessoa(Pessoa pessoa){
        String sql="INSERT INTO pessoa (nome,email) VALUES (?,?)";
        try(Connection conn=Conexao.getconexao(); PreparedStatement ps=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
            ps.setString(1,pessoa.getNome());
            ps.setString(2,pessoa.getEmail());

            ps.executeUpdate();

            try(ResultSet rs=ps.getGeneratedKeys()){
                if(rs.next()){
                    pessoa.setId(rs.getInt(1));
                }

            }

            System.out.println("Pessoa cadastrado com sucesso:\n"+pessoa);

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar pessoa"+e.getMessage());
        }


    }

    public List<Pessoa> listarPessoas(){
        String sql="SELECT * FROM pessoa";
        List<Pessoa> pessoas=new ArrayList<>();

        try(Connection conn=Conexao.getconexao(); PreparedStatement ps=conn.prepareStatement(sql);ResultSet rs=ps.executeQuery();){
            while(rs.next()){
                Pessoa pessoa=new Pessoa(rs.getString("nome"),rs.getString("email"));
                pessoa.setId(rs.getInt("id"));
                pessoas.add(pessoa);

            }

        } catch (Exception e) {
            System.out.println("Erro ao listar pessoas"+e.getMessage());
        }

        return pessoas;

    }

    public Pessoa BuscarPessoaPorId(int id){
        String sql="SELECT * FROM pessoa WHERE id=?";
        try(Connection conn=Conexao.getconexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,id);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                Pessoa pessoa=new Pessoa(rs.getString("nome"),rs.getString("email"));
                pessoa.setId(rs.getInt("id"));
                return pessoa;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar pessoa"+e.getMessage());
        }

        return null;

    }

    public void AtualizarPessoaPorId(Pessoa pessoa,int id){
        String sql="UPDATE pessoa SET nome=?,email=? WHERE id=?";
        try(Connection conn=Conexao.getconexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setString(1,pessoa.getNome());
            ps.setString(2,pessoa.getEmail());
            ps.setInt(3,id);

            int afetado=ps.executeUpdate();
            if(afetado>0){
                System.out.println("Pessoa atualizado com sucesso!");
            }else{
                System.out.println("Erro ao atualizar pessoa!");
            }

        }catch(Exception e){
            System.out.println("Erro ao atualizar pessoa"+e.getMessage());
        }

    }

    public void DeletarPessoaPorId(int id){
        String sql="DELETE FROM pessoa WHERE id=?";
        try(Connection conn=Conexao.getconexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,id);

            int afetado=ps.executeUpdate();

            if(afetado>0){
                System.out.println("Pessoa deletado com sucesso!");
            }else{
                System.out.println("Erro ao deletar pessoa!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao deletar pessoa"+e.getMessage());
        }
    }

}
