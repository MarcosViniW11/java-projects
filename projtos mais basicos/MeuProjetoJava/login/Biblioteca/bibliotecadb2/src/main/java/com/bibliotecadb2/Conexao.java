package com.bibliotecadb2;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private static String url="jdbc:mysql://localhost:3306/bibliotecadb2";
    private static String user="root";
    private static String password="";

    public static Connection getConexao() throws Exception{
        return DriverManager.getConnection(url,user,password);

    }


}
