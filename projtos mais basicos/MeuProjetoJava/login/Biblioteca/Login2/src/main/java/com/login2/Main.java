package com.login2;


import java.util.List;

public class Main {
    public static void main(String[] args) {

        PessoaDAO dao = new PessoaDAO();

        Pessoa p1=new Pessoa("Carlos","Carlos@gmail.com");

        //dao.CadastrarPessoa(p1);

        List<Pessoa> pessoas=dao.listarPessoas();
        for(Pessoa p:pessoas){
            System.out.println(p);
        }

        Pessoa PEN=dao.BuscarPessoaPorId(1);
        System.out.println("PESSOA ENCONTRADA: "+PEN);

        //dao.AtualizarPessoaPorId(p1,1);
        dao.DeletarPessoaPorId(1);



    }
}