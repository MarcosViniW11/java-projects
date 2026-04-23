package com.Pessoas;


import java.util.List;

public class Main {
    public static void main(String[] args) {

        PessoasDao dao = new PessoasDao();
        Pessoas pessoa1=new Pessoas("Marcos","Marcos@gmail.com");
        //dao.CadastrarPessoa(pessoa1);

        //Pessoas pessoaEcontrada=dao.BuscarPessoa(2);
        //System.out.println("Pessoa Encontrada "+pessoaEcontrada);
        //dao.AtualizarPessoa(pessoa1,2);

        dao.DeletarPessoa(3);

        List<Pessoas> listaPessoas=dao.listarPessoas();
         for(Pessoas p:listaPessoas){
            System.out.println(p);
        }

        //dao.listarPessoas();





    }
}