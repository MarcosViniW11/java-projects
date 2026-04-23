package com.Cadastro2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static String URL = "jdbc:mysql://localhost:3306/cadastro3";
    private static String USUARIO = "root";
    private static String SENHA = "";

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL,USUARIO,SENHA);
    }

}
