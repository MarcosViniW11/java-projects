package com.Pessoas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static String url = "jdbc:mysql://localhost:3306/pessoas";
    private static String user = "root";
    private static String password = "";

    public static Connection getconexao() throws SQLException{
        return DriverManager.getConnection(url,user,password);
    }


}
