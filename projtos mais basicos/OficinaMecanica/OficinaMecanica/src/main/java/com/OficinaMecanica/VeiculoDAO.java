package com.OficinaMecanica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {

    public void CadastrarVeiculo(Veiculo v){
        String sql="INSERT INTO veiculo (idCliente,modelo,placa,ano,statos) VALUES (?,?,?,?,?)";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
            ps.setInt(1,v.getIdCliente());
            ps.setString(2,v.getModelo());
            ps.setString(3,v.getPlaca());
            ps.setInt(4,v.getAno());
            ps.setString(5,v.getStatus());
            ps.executeUpdate();

            try(ResultSet rs=ps.getGeneratedKeys()){
                if(rs.next()){
                    v.setId(rs.getInt(1));
                }
            }
            System.out.println("Veiculo cadastrado com sucesso");

        } catch (Exception e) {
            System.out.println("Erro ao CadastrarVeiculo"+e.getMessage());
        }

    }

    public void AtualizarVeiculoPorId(Veiculo v,int id){
        String sql="UPDATE veiculo set idCliente=?,modelo=?,placa=?,ano=?,statos=? where id=?";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,v.getIdCliente());
            ps.setString(2,v.getModelo());
            ps.setString(3,v.getPlaca());
            ps.setInt(4,v.getAno());
            ps.setString(5,v.getStatus());
            ps.setInt(6,id);
            int afetado=ps.executeUpdate();
            if(afetado>0){
                System.out.println("Veiculo atualizado com sucesso");
            }

        } catch (Exception e) {
            System.out.println("Erro ao AtualizarVeiculoPorId"+e.getMessage());
        }
    }

    public void ExcluirVeiculoPorId(int id){
        String sql="DELETE FROM veiculo WHERE id=?";
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,id);
            int afetado=ps.executeUpdate();
            if(afetado>0){
                System.out.println("Veiculo deletado com sucesso");
            }

        } catch (Exception e) {
            System.out.println("Erro ao ExcluirVeiculoPorId"+e.getMessage());
        }
    }

    public List<Veiculo> ListarVeiculos(){
        String sql="SELECT * FROM veiculo";
        List<Veiculo> listaVeiculos=new ArrayList<>();
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);ResultSet rs=ps.executeQuery();){
            while(rs.next()){
                Veiculo v = new Veiculo(rs.getInt("idCliente"),rs.getString("modelo"),rs.getString("placa"),rs.getInt("ano"),rs.getString("statos"));
                v.setId(rs.getInt("id"));
                listaVeiculos.add(v);
            }

        } catch (Exception e) {
            System.out.println("Erro ao ListarVeiculos"+e.getMessage());
        }
        return listaVeiculos;
    }

    public List<Veiculo> ListarVeiculosPorCliente(int idCliente){
        String sql="SELECT * FROM veiculo WHERE idCliente=?";
        List<Veiculo> listaVeiculos=new ArrayList<>();
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setInt(1,idCliente);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Veiculo veiculo=new Veiculo(rs.getInt("idCliente"),rs.getString("modelo"),rs.getString("placa"),rs.getInt("ano"),rs.getString("statos"));
                veiculo.setId(rs.getInt("id"));
                listaVeiculos.add(veiculo);
            }
        } catch (Exception e) {
            System.out.println("Erro ao ListarVeiculos"+e.getMessage());
        }
        return listaVeiculos;
    }

    public Veiculo BuscarVeiculoPorPlaca(String placa){
        String sql="SELECT * FROM veiculo WHERE placa=?";
        Veiculo v=new Veiculo();
        try(Connection conn=Conexao.getConexao(); PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setString(1,placa);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                v.setId(rs.getInt("id"));
                v.setIdCliente(rs.getInt("idCliente"));
                v.setModelo(rs.getString("modelo"));
                v.setPlaca(rs.getString("placa"));
                v.setAno(rs.getInt("ano"));
                v.setStatus(rs.getString("statos"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao BuscarVeiculoPorPlaca"+e.getMessage());
        }
        return v;
    }


}
