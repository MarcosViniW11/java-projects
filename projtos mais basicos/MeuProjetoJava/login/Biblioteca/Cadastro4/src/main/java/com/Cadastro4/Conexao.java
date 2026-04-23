package com.Cadastro4;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private static String url = "jdbc:mysql://localhost:3306/cadastro4";
    private static String usuario = "root";
    private static String senha = "";

    public static Connection getConexao() throws Exception{
        return DriverManager.getConnection(url,usuario,senha);
    }


}
