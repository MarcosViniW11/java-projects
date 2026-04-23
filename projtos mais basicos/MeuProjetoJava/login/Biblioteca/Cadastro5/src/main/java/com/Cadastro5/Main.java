package com.Cadastro5;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        PessoaDAO dao = new PessoaDAO();
        Pessoa p1=new Pessoa("Ana","Ana@gmail.com");
        //dao.CadastrarPessoa(p1);
        System.out.println("LISTA PESSOA:");
        List<Pessoa> listaPessoas=dao.listarPessoas();
        for(Pessoa p:listaPessoas){
            System.out.println(p);
        }

        //dao.AtualizarPessoaPorId(p1,1);
        //dao.ExcluirPessoaPorId(1);





    }
}