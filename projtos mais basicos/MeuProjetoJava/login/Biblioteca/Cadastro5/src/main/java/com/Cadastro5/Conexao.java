package com.Cadastro5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static String URL = "jdbc:mysql://localhost:3306/cadastro5";
    private static String USUARIO = "root";
    private static String SENHA = "";

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL,USUARIO,SENHA);
    }

}
