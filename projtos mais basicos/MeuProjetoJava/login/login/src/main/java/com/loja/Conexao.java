package com.loja;
//extensões

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static String conexao="jdbc:mysql://localhost:3306/login";
    private static String usuario="root";
    private static String senha="";

    public static Connection getConexao() throws SQLException{
        return DriverManager.getConnection(conexao,usuario,senha);
    }

}
