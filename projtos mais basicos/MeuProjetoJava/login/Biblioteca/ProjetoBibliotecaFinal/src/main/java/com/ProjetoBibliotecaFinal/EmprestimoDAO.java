package com.ProjetoBibliotecaFinal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {

    public Boolean CadastrarEmprestimo(Emprestimo emprestimo){
        String insert="INSERT INTO emprestimo (idUsuario,idLivro,dataEmprestimo,dataDevolucao,statos) VALUES (?,?,?,?,?)";
        String update="UPDATE livro SET statos='Emprestado' WHERE id=?";
        String incrementar="UPDATE usuario SET qtbEmprestimosAtivos=qtbEmprestimosAtivos+1 WHERE id=?";

        try(Connection conn=Conexao.getConexao()){
            int idLivro;
            int idUsuario;
            conn.setAutoCommit(false);

            try(PreparedStatement ps=conn.prepareStatement(insert,PreparedStatement.RETURN_GENERATED_KEYS)){
                ps.setInt(1,emprestimo.getIdUsuario());
                ps.setInt(2,emprestimo.getIdLivro());
                ps.setDate(3,new Date(emprestimo.getDataEmprestimo().getTime()));
                ps.setDate(4,new Date(emprestimo.getDataDevolucao().getTime()));
                ps.setString(5,emprestimo.getStatos());

                ps.executeUpdate();

                try(ResultSet rs=ps.getGeneratedKeys()){
                    if(!rs.next()){
                        System.out.println("Ocorreu um erro ao criar o emprestimo");
                        conn.rollback();
                        return false;
                    }
                    emprestimo.setId(rs.getInt(1));
                    idLivro=emprestimo.getIdLivro();
                    idUsuario=emprestimo.getIdUsuario();

                }
            }

            try(PreparedStatement ps=conn.prepareStatement(update)){
                ps.setInt(1,idLivro);

                int afetadas=ps.executeUpdate();

                if(afetadas==0 || afetadas < 0){
                    System.out.println("Ocorreu um erro ao Emprestar o livro");
                    conn.rollback();
                    return false;
                }

                System.out.println("Livro Emprestado com sucesso");
            }

            try(PreparedStatement ps=conn.prepareStatement(incrementar)){
                ps.setInt(1,idUsuario);
                int afetadas=ps.executeUpdate();

                if(afetadas==0 || afetadas < 0){
                    System.out.println("Ocorreu um erro ao implementar mais um imprestimo no usuario");
                    conn.rollback();
                    return false;
                }
                System.out.println("Mais um usuario com emprestimo cadastrado com sucesso");
            }

            System.out.println("Emprestimo Cadastrado com sucesso");
            conn.commit();
            return true;





        } catch (Exception e) {
            System.out.println("Erro ao criar Emprestimo  \n"+e.getMessage());
            return false;
        }



    }

    public List<Emprestimo> ListarEmprestimos(){
        String select="SELECT * FROM emprestimo";
        List<Emprestimo> emprestimos=new ArrayList<>();
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(select);ResultSet rs=ps.executeQuery();){
            while(rs.next()){
                Emprestimo emprestimo=new Emprestimo(rs.getInt("idUsuario"),rs.getInt("idLivro"),rs.getDate(("dataEmprestimo")),
                        rs.getDate("dataDevolucao"),rs.getString("statos"));
                emprestimo.setId(rs.getInt("id"));
                emprestimos.add(emprestimo);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar Emprestimos"+e.getMessage());
        }
        return emprestimos;

    }

    public List<Emprestimo> ListarEmprestimosPorUsuario(int idUsuario){
        String sql="SELECT * FROM emprestimo WHERE idUsuario=?";
        List<Emprestimo> emprestimos=new ArrayList<>();
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,idUsuario);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Emprestimo emprestimo=new Emprestimo(rs.getInt("idUsuario"),rs.getInt("idLivro"),rs.getDate("dataEmprestimo"),
                        rs.getDate("dataDevolucao"),rs.getString("statos"));
                emprestimo.setId(rs.getInt("id"));
                emprestimos.add(emprestimo);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar Emprestimos De um usuario:"+e.getMessage());
        }
        return emprestimos;


    }

    public Boolean RegistrarDevolucao(int idEmprestimo){
        String select="SELECT * FROM emprestimo WHERE id=?";
        String Update="UPDATE emprestimo SET statos='Concluido' WHERE id=?";
        String UpdateLivro="UPDATE livro SET statos='Disponivel' WHERE id=?";
        String UpdateUsuario="UPDATE usuario SET qtbEmprestimosAtivos=qtbEmprestimosAtivos-1 WHERE id=?";

        try(Connection conn=Conexao.getConexao();){
            int idLivro=0;
            int idUsuario=0;
            conn.setAutoCommit(false);

            try(PreparedStatement ps=conn.prepareStatement(select);){
                ps.setInt(1,idEmprestimo);

                ResultSet rs=ps.executeQuery();
                /*if(!rs.next()){
                    System.out.println("ERRO ao encontrar o emprestimo");
                    conn.rollback();
                    return false;
                }*/

                if(rs.next()){
                    Emprestimo emprestimo=new Emprestimo(rs.getInt("idUsuario"),rs.getInt("idLivro"),rs.getDate("dataEmprestimo"),rs.getDate("dataDevolucao"),rs.getString("statos"));
                    emprestimo.setId(rs.getInt("id"));
                    idLivro=emprestimo.getIdLivro();
                    idUsuario=emprestimo.getIdUsuario();
                }
            }

            try(PreparedStatement ps=conn.prepareStatement(Update);){
                ps.setInt(1,idEmprestimo);
                int afetadas=ps.executeUpdate();
                if(afetadas==0 || afetadas < 0){
                    System.out.println("erro ao editar o emprestimo");
                    conn.rollback();
                    return false;
                }
                System.out.println("Emprestimo editado com sucesso");

            }

            if (idLivro == 0 || idUsuario == 0) {
                System.out.println("Erro: empréstimo não encontrado ou dados incompletos.");
                conn.rollback();
                return false;
            }

            try(PreparedStatement ps=conn.prepareStatement(UpdateLivro);){
                ps.setInt(1,idLivro);
                int afetadas=ps.executeUpdate();
                if(afetadas == 0){
                    System.out.println("erro ao editar o Livro");
                    conn.rollback();
                    return false;
                }
                System.out.println("Livro editado com sucesso");
            }

            try(PreparedStatement ps=conn.prepareStatement(UpdateUsuario);){
                ps.setInt(1,idUsuario);
                int afetadas=ps.executeUpdate();
                if(afetadas==0 || afetadas < 0){
                    System.out.println("erro ao editar o Usuario");
                    conn.rollback();
                    return false;
                }
                System.out.println("Usuario editado com sucesso");
            }
            conn.commit();
            return true;


        } catch (Exception e) {
            System.out.println("Erro ao registrar Devolução"+e.getMessage());
            return false;
        }


    }

    public Boolean AtualizarStatusEmprestimoAtrasado(){
        String select="SELECT * FROM emprestimo WHERE statos='Ativo'";
        String Update="UPDATE emprestimo SET statos='Atrasado' WHERE id=?";


        try(Connection conn=Conexao.getConexao();){
            conn.setAutoCommit(false);
            int totEmprestimosAtrasado=0;

            try(PreparedStatement ps=conn.prepareStatement(select);){
                ResultSet rs=ps.executeQuery();
                /*if(!rs.next()){
                    System.out.println("ERRO ao Buscar os emprestimos");
                    conn.rollback();
                    return false;
                }*/
                while(rs.next()){
                    Emprestimo emprestimo=new Emprestimo(rs.getInt("idUsuario"),rs.getInt("idLivro"),rs.getDate("dataEmprestimo"),rs.getDate("dataDevolucao"),rs.getString("statos"));
                    emprestimo.setId(rs.getInt("id"));
                    int id = emprestimo.getId();
                    Date dataAtual=new Date(System.currentTimeMillis());
                    Date dataDevolucao=rs.getDate("dataDevolucao");

                    if(dataDevolucao.getTime() < dataAtual.getTime()){ //dataDevolucao.after(dataAtual)
                        try(PreparedStatement psUpdate=conn.prepareStatement(Update)){
                            psUpdate.setInt(1,id);
                            int afetadas=psUpdate.executeUpdate();
                            if(afetadas==0 || afetadas < 0){
                                System.out.println("erro ao atualizar o emprestimo");
                                conn.rollback();
                                return false;
                            }
                            totEmprestimosAtrasado++;


                        }

                    }



                }


            }
            System.out.println("Emprestimos atualizado com sucesso,tivemos um total de : "+totEmprestimosAtrasado+" emprestimos Atrasado(s)");
            conn.commit();
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao Atualizar Status dos Emprestimos atrasados\n"+e.getMessage());
            return false;
        }



    }

    public List<Emprestimo> ListarEmprestimosAtivos(){
        String select="SELECT * FROM emprestimo WHERE statos='Ativo'";
        List<Emprestimo> emprestimosAtivos=new ArrayList<>();
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(select);ResultSet rs=ps.executeQuery();){
            while(rs.next()){
                Emprestimo emprestimo=new Emprestimo(rs.getInt("idUsuario"),rs.getInt("idLivro"),rs.getDate("dataEmprestimo"),rs.getDate("dataDevolucao"),rs.getString("statos"));
                emprestimo.setId(rs.getInt("id"));
                emprestimosAtivos.add(emprestimo);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar Emprestimos"+e.getMessage());
        }
        return emprestimosAtivos;
    }

    public List<Emprestimo> ListarEmprestimosAtivosPorUsuario(int idUsuario){
        String select="SELECT * FROM emprestimo WHERE statos='Ativo' and idUsuario=?";
        List<Emprestimo> emprestimosAtivos=new ArrayList<>();
        int qtbEmprestimosAtivos=0;
        try(Connection conn=Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(select);){
            ps.setInt(1,idUsuario);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Emprestimo emprestimo=new Emprestimo(rs.getInt("idUsuario"),rs.getInt("idLivro"),rs.getDate("dataEmprestimo"),rs.getDate("dataDevolucao"),rs.getString("statos"));
                emprestimo.setId(rs.getInt("id"));
                emprestimosAtivos.add(emprestimo);
                qtbEmprestimosAtivos++;
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar Emprestimos"+e.getMessage());
        }
        System.out.println("Este usuario possui "+qtbEmprestimosAtivos+ " emprestimos Ativos");
        return emprestimosAtivos;
    }

    public boolean PodeEmprestar(int idUsuario) {
        String sql = "SELECT qtbEmprestimosAtivos, limiteEmprestimos FROM usuario WHERE id=?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int ativos = rs.getInt("qtbEmprestimosAtivos");
                int limite = rs.getInt("limiteEmprestimos");
                return ativos < limite;
            }
        } catch (Exception e) {
            System.out.println("Erro ao verificar limite: " + e.getMessage());
        }
        return false;
    }



}
