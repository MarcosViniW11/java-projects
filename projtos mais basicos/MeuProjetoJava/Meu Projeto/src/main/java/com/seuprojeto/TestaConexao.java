package com.seuprojeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestaConexao {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb"; // testdb = nome do seu banco
        String usuario = "root"; // se não alterou no XAMPP
        String senha = "";       // se não tem senha no phpMyAdmin, deixe vazio

        try {
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("✅ Conectado com sucesso ao banco de dados!");
            conexao.close();
        } catch (SQLException e) {
            System.out.println("❌ Erro ao conectar: " + e.getMessage());
        }
    }
}
