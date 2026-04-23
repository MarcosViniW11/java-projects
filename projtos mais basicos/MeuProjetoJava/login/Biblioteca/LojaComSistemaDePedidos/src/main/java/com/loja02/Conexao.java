package com.loja02;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private static String url="jdbc:mysql://localhost:3306/loja02";
    private static String user="root";
    private static String password="";

    public static Connection getConexao() throws Exception{
        return DriverManager.getConnection(url,user,password);
    }

}
