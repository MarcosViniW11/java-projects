package com.seuprojeto;


public class Main {
    public static void main(String[] args) {
        UsuarioDAO usuario= new UsuarioDAO();

        usuario.inserir(new Usuario("Felipe","Felipe022@gmail.com"));
        usuario.inserir(new Usuario("Mariana","Mariana077@gmail.com"));

        for(Usuario u: usuario.listar()){
            System.out.println(u);
        }



    }
}