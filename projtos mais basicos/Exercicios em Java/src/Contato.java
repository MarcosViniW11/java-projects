public class Contato {
    private String nome;
    private String Telefone;

    public Contato(String nome, String telefone) {
        this.nome = nome;
        this.Telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return Telefone;
    }
    public void setTelefone(String telefone) {
        this.Telefone = telefone;
    }

    @Override
    public String toString() {
        return nome +" - "+ Telefone;
    }

}
