package com.SistemaDeAcademia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static String url="jdbc:mysql://localhost:3306/sistema_de_academia";
    private static String user="root";
    private static String password="";

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

}
