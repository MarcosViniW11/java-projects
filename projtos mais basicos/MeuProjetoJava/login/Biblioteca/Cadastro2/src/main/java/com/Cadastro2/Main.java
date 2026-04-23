package com.Cadastro2;


import java.util.List;

public class Main {
    public static void main(String[] args) {

        PessoaDAO dao = new PessoaDAO();

        Pessoa p=new Pessoa("Antonio","Antonio@gmail.com");
        //dao.CadastrarPessoa(p);

        //List<Pessoa> listaPessoas = dao.listarPessoas();
        //for(Pessoa p : listaPessoas){
          //  System.out.println(p);
        //}

        //Pessoa PEncontrada=dao.buscarPessoa(1);
        //System.out.println(PEncontrada);

        //dao.AtualizarPessoaPorId(p,1);
        dao.DeletarPessoaPorId(2);


    }
}