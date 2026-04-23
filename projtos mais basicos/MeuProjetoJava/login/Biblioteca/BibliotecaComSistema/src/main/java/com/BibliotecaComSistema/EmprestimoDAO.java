package com.BibliotecaComSistema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmprestimoDAO {

    public Boolean CadastrarEmprestimo(Emprestimo emprestimo) {
        String sqlLivro = "SELECT quantidade FROM livro WHERE id = ?";
        String sqlEmprestimo = "INSERT INTO emprestimo(usuarioid, livroid, dataEmprestimo, dataDevolucao, dataPrevista) VALUES (?, ?, ?, ?, ?)";
        String sqlUpdateLivro = "UPDATE livro SET quantidade = quantidade - 1 WHERE id = ?";

        try (Connection conn = Conexao.getConexao()) {
            try {
                conn.setAutoCommit(false);

                // 1) Verificar estoque
                int quantidade = 0;
                try (PreparedStatement ps = conn.prepareStatement(sqlLivro)) {
                    ps.setInt(1, emprestimo.getLivroId());
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        System.out.println("Livro não encontrado!");
                        conn.rollback();
                        return false;
                    }
                    quantidade = rs.getInt("quantidade");
                }

                if (quantidade <= 0) {
                    System.out.println("⚠ Estoque insuficiente!");
                    conn.rollback();
                    return false;
                }

                // 2) Inserir empréstimo
                try (PreparedStatement ps = conn.prepareStatement(sqlEmprestimo)) {
                    ps.setInt(1, emprestimo.getUsuarioId());
                    ps.setInt(2, emprestimo.getLivroId());
                    ps.setDate(3, new java.sql.Date(emprestimo.getDataEmprestimo().getTime()));
                    ps.setNull(4, java.sql.Types.DATE); // devolução começa NULL
                    ps.setDate(5, new java.sql.Date(emprestimo.getDataPrevista().getTime()));

                    int afetadas = ps.executeUpdate();
                    if (afetadas <= 0) {
                        System.out.println("❌ Falha ao cadastrar empréstimo.");
                        conn.rollback();
                        return false;
                    }
                }

                // 3) Atualizar estoque do livro
                try (PreparedStatement ps = conn.prepareStatement(sqlUpdateLivro)) {
                    ps.setInt(1, emprestimo.getLivroId());
                    int afetadas = ps.executeUpdate();
                    if (afetadas <= 0) {
                        System.out.println("❌ Falha ao atualizar estoque do livro.");
                        conn.rollback();
                        return false;
                    }
                }

                // 4) Commit final
                conn.commit();
                System.out.println("✅ Empréstimo cadastrado com sucesso!");
                return true;

            } catch (Exception e) {
                conn.rollback();
                System.out.println("Erro: " + e.getMessage());
                return false;
            }

        } catch (Exception e) {
            System.out.println("Erro de conexão: " + e.getMessage());
            return false;
        }
    }

    public List<EmprestimoDetalhe> ListarEmprestimosComDetalhe() {
        String sql = "SELECT e.id, u.nome AS usuarioNome, l.titulo AS livroTitulo, " +
                "e.dataEmprestimo, e.dataPrevista, e.dataDevolucao " +
                "FROM emprestimo e " +
                "JOIN usuario u ON e.usuarioid = u.id " +
                "JOIN livro l ON e.livroid = l.id";

        List<EmprestimoDetalhe> emprestimos = new ArrayList<>();

        try(Connection conn = Conexao.getConexao()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EmprestimoDetalhe emprestimo = new EmprestimoDetalhe();
                emprestimo.setId(rs.getInt("id"));
                emprestimo.setUsuarioNome(rs.getString("usuarioNome"));
                emprestimo.setLivroTitulo(rs.getString("livroTitulo"));
                emprestimo.setDataEmprestimo(rs.getDate("dataEmprestimo"));
                emprestimo.setDataPrevista(rs.getDate("dataPrevista"));
                emprestimo.setDataDevolucao(rs.getDate("dataDevolucao"));

                emprestimos.add(emprestimo);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return emprestimos;

    }

    public Boolean ExcluirEmprestimo(int id) {
        String sqlSelect="Select * from emprestimo where id=?";
        String sqlDelete="Delete from emprestimo where id=?";
        String sqlInsert="INSERT INTO emprestimo_finalizado (usuarioid,livroid,dataEmprestimo,dataDevolucao) VALUES (?,?,?,?)";
        String sqlUpdate="Update livro set quantidade = quantidade + 1 where id=?";



        try(Connection conn=Conexao.getConexao()){
            conn.setAutoCommit(false);
            int idEmprestimo;
            int idUsuario;
            int idLivro;
            Date dataEmprestimo;
            Date dataDevolucao;

            try(PreparedStatement ps = conn.prepareStatement(sqlSelect)){
                ps.setInt(1, id);

                try(ResultSet rs = ps.executeQuery()){
                    if(!rs.next()){
                        System.out.println("Emprestimo não encontrado!");
                        conn.rollback();
                        return false;
                    }
                    idEmprestimo = rs.getInt("id");
                    idUsuario = rs.getInt("usuarioid");
                    idLivro = rs.getInt("livroid");
                    dataEmprestimo = rs.getDate("dataEmprestimo");
                    dataDevolucao = rs.getDate("dataDevolucao");
                }
            }

            try(PreparedStatement ps = conn.prepareStatement(sqlDelete)){
                ps.setInt(1, id);

                int afetadas = ps.executeUpdate();
                if (afetadas <= 0) {
                    System.out.println("Não foi possivel excluir o emprestimo.");
                    conn.rollback();
                    return false;
                }

            }

            try(PreparedStatement ps = conn.prepareStatement(sqlInsert)){
                ps.setInt(1, idUsuario);
                ps.setInt(2, idLivro);
                ps.setDate(3, new java.sql.Date(dataEmprestimo.getTime()));
                ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));

                int afetadas = ps.executeUpdate();
                if (afetadas <= 0) {
                    System.out.println("houve um erro ao copiar informações do emprestimo.");
                    conn.rollback();
                    return false;
                }

            }

            try(PreparedStatement ps = conn.prepareStatement(sqlUpdate)){
                ps.setInt(1, idLivro);
                int afetadas = ps.executeUpdate();
                if (afetadas <= 0) {
                    System.out.println("ocorreu um erro ao devolver quantidade ao estoque");
                    conn.rollback();
                    return false;
                }
            }

            conn.commit();
            System.out.println("Emprestimo removido com sucesso! e quantidade devolvida ao estoque");
            return true;





        } catch (Exception e) {
            System.out.println("OCORREU UM ERRO"+e.getMessage());
            return false;
        }

    }

    public List<emprestimoFinalizado> ListarEmprestimosFinalizados(){
        String sqlSelect="Select * from emprestimo_finalizado";
        List<emprestimoFinalizado> emprestimos=new ArrayList<>();

        try(Connection conn=Conexao.getConexao();PreparedStatement ps = conn.prepareStatement(sqlSelect);ResultSet rs=ps.executeQuery();){
            while(rs.next()){
                emprestimoFinalizado emprestimo=new emprestimoFinalizado();
                emprestimo.setId(rs.getInt("id"));
                emprestimo.setIdUsuario(rs.getInt("usuarioid"));
                emprestimo.setIdLivro(rs.getInt("livroid"));
                emprestimo.setDataEmprestimo(rs.getDate("dataEmprestimo"));
                emprestimo.setDataDevolucao(rs.getDate("dataDevolucao"));

                emprestimos.add(emprestimo);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return emprestimos;

    }


}
