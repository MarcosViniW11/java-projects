package com.login2;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private static String url = "jdbc:mysql://localhost:3306/cadastro2";
    private static String usuario = "root";
    private static String senha = "";

    public static Connection getconexao() throws Exception{

        return DriverManager.getConnection(url,usuario,senha);

    }

}
