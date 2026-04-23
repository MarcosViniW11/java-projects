import java.time.LocalDate;

public class Emprestimo {
    private Livro livro;
    private String usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(Livro livro, String usuario) {
        this(livro, usuario, LocalDate.now(), null);
    }

    public Emprestimo(Livro livro, String usuario, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Livro getLivro() { return livro; }
    public String getUsuario() { return usuario; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(LocalDate dataDevolucao) { this.dataDevolucao = dataDevolucao; }

    @Override
    public String toString() {
        String status = (dataDevolucao == null) ? "🔴 Em aberto" : "🟢 Devolvido em " + dataDevolucao;
        return "Livro: " + livro.getTitulo() +
                " | Usuário: " + usuario +
                " | Empréstimo: " + dataEmprestimo +
                " | " + status;
    }
}
