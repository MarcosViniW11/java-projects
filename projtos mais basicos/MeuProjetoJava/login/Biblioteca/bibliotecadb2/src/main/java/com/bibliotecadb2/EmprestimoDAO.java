package com.bibliotecadb2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {

    // CADASTRAR EMPRESTIMO
    public void CadastrarEmprestimo(Emprestimo emprestimo){
        String sql="INSERT INTO emprestimos (usuario_id, livro_id, data_emprestimo, data_devolucao) VALUES (?,?,?,?)";

        try(Connection conn = Conexao.getConexao();
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, emprestimo.getUsuarioId());  // <-- só IDs
            ps.setInt(2, emprestimo.getLivroId());
            ps.setDate(3, new Date(emprestimo.getDataEmprestimo().getTime()));
            ps.setDate(4, new Date(emprestimo.getDataDevolucao().getTime()));

            ps.executeUpdate();

            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    emprestimo.setId(rs.getInt(1));
                }
            }
            System.out.println("✅ Empréstimo cadastrado com sucesso!!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // LISTAR EMPRESTIMOS
    public List<Emprestimo> listarEmprestimos(){
        String sql = "SELECT * FROM emprestimos"; // só pega os IDs, datas e devolvido
        List<Emprestimo> lista = new ArrayList<>();

        try(Connection conn = Conexao.getConexao();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()){
                Emprestimo emp = new Emprestimo();
                emp.setId(rs.getInt("id"));
                emp.setUsuarioId(rs.getInt("usuario_id"));
                emp.setLivroId(rs.getInt("livro_id"));
                emp.setDataEmprestimo(rs.getDate("data_emprestimo"));
                emp.setDataDevolucao(rs.getDate("data_devolucao"));
                emp.setDevolvido(rs.getBoolean("devolvido"));

                lista.add(emp);
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return lista;
    }
}
