package br.com.marcos.biblioteca.service;

import br.com.marcos.biblioteca.model.Emprestimo;
import br.com.marcos.biblioteca.repository.EmprestimoRepository;
import org.springframework.stereotype.Service;
import br.com.marcos.biblioteca.model.Livro;
import br.com.marcos.biblioteca.repository.LivroRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    private EmprestimoRepository repoEmprestimo;
    private LivroRepository repoLivro;

    public EmprestimoService(EmprestimoRepository repoEmprestimo, LivroRepository repoLivro) {
        this.repoEmprestimo = repoEmprestimo;
        this.repoLivro = repoLivro;
    }

    public List<Emprestimo> Listar(){
        return repoEmprestimo.findAll();
    }

    public Optional<Emprestimo> buscarPorId(Long id){
        return repoEmprestimo.findById(id);
    }

    public Emprestimo salvar(Emprestimo emprestimo){

        Livro livro=repoLivro.findById(emprestimo.getLivro().getId()).orElseThrow(() -> new IllegalArgumentException("Livro não encontrado."));

        if (livro.getQuantidadeEstoque() <= 0) {
            throw new IllegalStateException("Livro indisponível para empréstimo.");
        }

        livro.setQuantidadeEstoque(livro.getQuantidadeEstoque()-1);
        repoLivro.save(livro);

        emprestimo.setLivro(livro);
        return repoEmprestimo.save(emprestimo);
    }

    public void remover(Long id){
        repoEmprestimo.findById(id).ifPresent(emprestimo->{
            Livro livro=emprestimo.getLivro();

            livro.setQuantidadeEstoque(livro.getQuantidadeEstoque()+1);
            repoLivro.save(livro);

            repoEmprestimo.deleteById(id);
        });

    }




}
