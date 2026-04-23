package com.loja;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        loginDAO dao = new loginDAO();

        //Pessoa p1=new Pessoa("Pedrão@gmail.com","Pedro");

        //dao.CadastrarPessoa(p1);

        //dao.deletarPessoa(8);


       System.out.println("Lista de pessoas Cadastradas");
       List<Pessoa> pessoas=dao.ListarPessoas();
       for(Pessoa p:pessoas){
            System.out.println("Pessoa: "+p);
       }
       //dao.deletarPessoa(2);
        Pessoa p=dao.BuscarPorId(5);
        System.out.println("Pessoa Encontrada: "+p);

        //Pessoa P=dao.BuscarPorId(p1.getId());
        //System.out.println("Pessoa: "+P);



    }
}