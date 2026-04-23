import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.time.LocalDate;


public class Biblioteca {
    private ArrayList<Livro> livros;
    private List<Emprestimo> emprestimos = new ArrayList<>();
    private final String arquivo = "livros.txt";
    private final String arquivoEmp = "emprestimos.txt";



    public Biblioteca(){
        this.livros = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
        livros = new ArrayList<>();
        carregarLivros();
        carregarEmprestimos();
    }

    private Livro getLivroPorIsbn(String isbn) {
        for (Livro l : livros) {
            if (l.getIsbn().trim().equalsIgnoreCase(isbn.trim())) return l;
        }
        return null;
    }

    private void salvarEmprestimos() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(arquivoEmp))) {
            for (Emprestimo e : emprestimos) {
                String dev = (e.getDataDevolucao() == null) ? "" : e.getDataDevolucao().toString();
                pw.println(e.getLivro().getIsbn() + ";" +
                        e.getUsuario() + ";" +
                        e.getDataEmprestimo().toString() + ";" +
                        dev);
            }
        } catch (IOException ex) {
            System.out.println("Erro ao salvar empréstimos: " + ex.getMessage());
        }
    }

    private void carregarEmprestimos() {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoEmp))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";", -1);
                if (d.length >= 3) {
                    Livro livro = getLivroPorIsbn(d[0]);
                    if (livro == null) continue; // livro não existe mais; ignora

                    String usuario = d[1];
                    LocalDate dataEmp = LocalDate.parse(d[2]);
                    LocalDate dataDev = (d.length >= 4 && !d[3].isBlank()) ? LocalDate.parse(d[3]) : null;

                    Emprestimo e = new Emprestimo(livro, usuario, dataEmp, dataDev);
                    emprestimos.add(e);

                    // mantém consistência: se não devolvido, o livro deve estar indisponível
                    if (dataDev == null) livro.setDisponivel(false);
                }
            }
        } catch (IOException ignore) { /* ok se ainda não existir */ }
    }

    public void adicionarLivro(String titulo, String autor, String isbn){
        if(titulo.isBlank() || autor.isBlank() || isbn.isBlank()){
            System.out.println("Titulo,autor,isbn não pode ser vazio!");
            return;
        }

        for(Livro l:livros){
            if(l.getIsbn().equalsIgnoreCase(isbn)){
                System.out.println("Ja existe um Livro com esse id Adicionado");
                return;
            }
        }
        livros.add(new Livro(titulo,autor,isbn));
        salvarLivros();
        System.out.println("Livro adicionado com sucesso");

    }

    public void MostrarEstatistica(){
        System.out.println("\n===== ESTATÍSTICAS DA BIBLIOTECA =====");

        System.out.println("Total de livros cadastrados: " + livros.size());

        HashSet<String> autores = new HashSet<>();

        for(Livro l:livros){
            autores.add(l.getAutor().toLowerCase());
        }

        System.out.println("Total de Autores: " + autores.size());
        System.out.println("======================================\n");

    }

    public void ListarLivros(){
        if(livros.isEmpty()){
            System.out.println("Nenhum livro cadastrado...");
            return;
        }
        System.out.println("Lista de Livros cadastrado:");
        int i=1;
        for(Livro l:livros){
            System.out.println(i +". "+ l);
            i++;
        }
    }

    public void ListarLivrosOrdenadoPorTitulo(){
        if(livros.isEmpty()){
            System.out.println("Nenhum livro cadastrado...");
            return;
        }
        livros.sort(Comparator.comparing(Livro::getTitulo,String.CASE_INSENSITIVE_ORDER));

        System.out.println("Lista de livros por Titulo:");
        int i=1;
        for(Livro l:livros){
            System.out.println(i +". "+ l);
            i++;
        }


    }

    public void ListarLivrosOrdenadoPorAutor(){
        if(livros.isEmpty()){
            System.out.println("Nenhum livro cadastrado...");
            return;
        }
        livros.sort(Comparator.comparing(Livro::getAutor,String.CASE_INSENSITIVE_ORDER));

        System.out.println("Lista de livros por Autor:");
        int i=1;
        for(Livro l:livros){
            System.out.println(i +". "+ l);
            i++;
        }
    }

    public void BuscarLivro(String titulo){
        boolean encontrado=false;
        for(Livro l:livros){
            if(l.getTitulo().toLowerCase().contains(titulo.toLowerCase())){
                System.out.println("Livro encontrado com sucesso"+l);
                encontrado=true;

            }
        }
        if(!encontrado){
            System.out.println("Livro não encontrado!");
        }


    }

    public void RemoverLivro(String isbn){
        int indice=-1;
        for(int i=0;i<livros.size();i++){
            if(livros.get(i).getIsbn().equalsIgnoreCase(isbn)){
                indice=i;
                break;
            }
        }
        if(indice>=0){
            Scanner sc = new Scanner(System.in);
            System.out.println(" Têm certeza de remover este livro "+livros.get(indice)+" s/n ");
            String res = sc.nextLine();
            if(res.equalsIgnoreCase("s")){
                livros.remove(indice);
                salvarLivros();
                System.out.println(" Livro removido com sucesso ");
            }else{
                System.out.println(" Remoção cancelada ");
            }
        }else{
            System.out.println("Livro não encontrado para remover!");
        }
    }

    private void salvarLivros(){
        try(PrintWriter pw=new PrintWriter(new FileWriter(arquivo))){
            for (Livro l:livros) {
                pw.println(l.getTitulo() + ";" + l.getAutor() + ";" + l.getIsbn() + ";" + l.isDisponivel());
            }
        } catch (Exception e) {
            System.out.println("Erro ao salvar o arquivo!"+e.getMessage());
        }
    }
    public void carregarDados() {
        livros.clear(); // limpa a lista atual para evitar duplicatas
        carregarLivros();
    }

    private void carregarLivros() {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";", -1);
                if (dados.length >= 3) { // ✅ aceita 3 ou 4 campos
                    String titulo = dados[0];
                    String autor  = dados[1];
                    String isbn   = dados[2];

                    if (!titulo.isBlank() && !autor.isBlank() && !isbn.isBlank()) {
                        boolean disp = true;
                        if (dados.length >= 4) {
                            String campo = dados[3].trim();
                            disp = "true".equalsIgnoreCase(campo) || "1".equals(campo);
                        }
                        livros.add(new Livro(titulo, autor, isbn, disp));
                    }
                }
            }
        } catch (IOException ignore) { }
    }


    public void editarLivro(String isbn){
        Livro livroParaEditar=null;

        for(Livro l : livros){
            if(l.getIsbn().equalsIgnoreCase(isbn)){
                livroParaEditar=l;
                break;
            }
        }

        if(livroParaEditar==null){
            System.out.println("Livro Não encontrado!");
            return;
        }
        Scanner sc = new Scanner(System.in);

        System.out.println("Livro econtrado com sucesso"+livroParaEditar);

        System.out.println("Novo titulo(se quiser manter o mesmo prescione enter)");
        String novoTitulo = sc.nextLine();
        if (!novoTitulo.isBlank()){
            livroParaEditar.setTitulo(novoTitulo);
        }

        System.out.println("Novo autor(se quiser Manter presione enter)");
        String NovoAutor = sc.nextLine();
        if (!NovoAutor.isBlank()){
            livroParaEditar.setAutor(NovoAutor);
        }

        System.out.println("Novo isbn(se quiser manter presione enter)");
        String NovoIsbn = sc.nextLine();
        if (!NovoIsbn.isBlank()){
            for(Livro l : livros){
                if(l.getIsbn().equalsIgnoreCase(NovoIsbn)){
                    System.out.println("Ja existe um livro com esse isbn,alteração cancelada");
                    return;
                }
            }
            livroParaEditar.setIsbn(NovoIsbn);
        }
        salvarLivros();
        System.out.println("Livro editado com sucesso"+livroParaEditar);

    }

    public void emprestarLivro(String isbn){
        for(Livro l:livros){
            if(l.getIsbn().equalsIgnoreCase(isbn)){
                if(!l.isDisponivel()){
                    System.out.println("Livro ja está Emprestado!"+l);
                    return;
                }
                l.setDisponivel(false);
                salvarLivros();
                System.out.println("Livro emprestado com sucesso"+l);
                return;

            }
        }
        System.out.println("Livro Não encontrado!");
    }

    public void devolverLivro(String isbn){
        for(Livro l:livros){
            if(l.getIsbn().equalsIgnoreCase(isbn)){
                if(l.isDisponivel()){
                    System.out.println("este Livro  ja esta marcado como disponivel"+l);
                    return;
                }
                l.setDisponivel(true);
                salvarLivros();
                System.out.println("Livro devolvido com sucesso"+l);
                return;
            }
        }
        System.out.println("Livro Não encontrado!");
    }

    public void listarDisponiveis(){
        boolean vazio=true;
        int i=1;
        for(Livro l:livros){
            if(l.isDisponivel()){
                if(vazio) System.out.println("\n Lista de livros disponiveis");
                System.out.println(i+". "+l);
                i++;
                vazio=false;

            }
        }
    if(vazio) System.out.println("Nenhum Livro disponivel no momento");
    }

    public void listarIndisponiveis(){
        boolean vazio=true;
        int i=1;
        for(Livro l:livros){
            if(!l.isDisponivel()){
                if(vazio) System.out.println("\n Lista de Livros indisponiveis");
                System.out.println(i+". "+l);
                i++;
                vazio=false;
            }
        }
        if(vazio) System.out.println("Nenhum Livro indisponiveis no momento");
    }

    public void registrarEmprestimo(String isbn, String usuario) {
        if (usuario == null || usuario.isBlank()) {
            System.out.println("Usuário inválido.");
            return;
        }
        for (Livro livro : livros) {
            if (livro.getIsbn().trim().equalsIgnoreCase(isbn.trim())) {
                if (!livro.isDisponivel()) {
                    System.out.println("Livro já está emprestado: " + livro);
                    return;
                }
                Emprestimo e = new Emprestimo(livro, usuario.trim());
                emprestimos.add(e);
                livro.setDisponivel(false);
                salvarLivros();
                salvarEmprestimos();
                System.out.println("Empréstimo registrado com sucesso para " + usuario + ": " + livro);
                return;
            }
        }
        System.out.println("Livro não encontrado para empréstimo!");
    }

    public void devolverLivro(String isbn, String usuario) {
        for (Emprestimo e : emprestimos) {
            boolean mesmoLivro = e.getLivro().getIsbn().trim().equalsIgnoreCase(isbn.trim());
            boolean mesmoUsuario = e.getUsuario().trim().equalsIgnoreCase(usuario.trim());
            if (mesmoLivro && e.getDataDevolucao() == null && mesmoUsuario) {
                e.getLivro().setDisponivel(true);
                e.setDataDevolucao(LocalDate.now());
                salvarLivros();
                salvarEmprestimos();
                System.out.println("Devolução registrada com sucesso: " + e.getLivro());
                return;
            }
        }
        System.out.println("Nenhum empréstimo ATIVO encontrado para este ISBN/usuário.");
    }

    public void ListarEmprestimos() {
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado.");
            return;
        }
        System.out.println("\n-- Empréstimos --");
        for (Emprestimo e : emprestimos) {
            System.out.println(e);
        }
    }


}
