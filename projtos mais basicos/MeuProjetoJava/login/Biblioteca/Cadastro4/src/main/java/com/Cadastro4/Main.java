package com.Cadastro4;


import java.util.List;

public class Main {
    public static void main(String[] args) {

        PessoaDAO dao = new PessoaDAO();
        Pessoa p1=new Pessoa("Ana Clara","AnaClara@gmail.com");
        //dao.CadastrarPessoa(p1);

        //List<Pessoa> listaPessoas = dao.listarPessoas();
        //for(Pessoa p : listaPessoas){
        //    System.out.println(p);
        //}

        //Pessoa pEncontrada=dao.buscarPessoaPorId(1);
        //System.out.println("Pessoa encontrada:"+pEncontrada);

        //dao.AtualizarPessoaPorId(p1,1);
        dao.ExcluirPessoaPorId(1);


    }
}