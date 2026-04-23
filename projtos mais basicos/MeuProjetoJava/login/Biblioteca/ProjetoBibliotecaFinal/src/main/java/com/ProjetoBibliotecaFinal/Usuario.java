package com.ProjetoBibliotecaFinal;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String tipo;
    private int qtbEmprestimosAtivos;
    private int limiteEmprestimos;
    private String statos;

    public Usuario(String nome,String email,String telefone,String tipo,int qtbEmprestimosAtivos,int limiteEmprestimos,String statos) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.tipo = tipo;
        this.qtbEmprestimosAtivos = qtbEmprestimosAtivos;
        this.limiteEmprestimos = limiteEmprestimos;
        this.statos = statos;
    }

    public Usuario(String nome,String email,String telefone,String tipo) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public int getQtbEmprestimosAtivos() {
        return qtbEmprestimosAtivos;
    }
    public void setQtbEmprestimosAtivos(int qtbEmprestimosAtivos) {
        this.qtbEmprestimosAtivos = qtbEmprestimosAtivos;
    }
    public int getLimiteEmprestimos() {
        return limiteEmprestimos;
    }
    public void setLimiteEmprestimos(int limiteEmprestimos) {
        this.limiteEmprestimos = limiteEmprestimos;
    }
    public String getStatos() {
        return statos;
    }
    public void setStatos(String statos) {
        this.statos = statos;
    }

    @Override
    public String toString() {
        return id+"; Nome:"+nome+"; email:"+email+"; telefone:"+telefone+"; tipo:"+tipo+" Quantidade de emprestimos ativos:"+qtbEmprestimosAtivos+"; Limite de emprestimos:"+limiteEmprestimos+"; Status:"+statos;
    }

}
