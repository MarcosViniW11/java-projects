import java.io.*;
import java.util.ArrayList;

public class Agenda {
    private ArrayList<Contato> contatos = new ArrayList<>();
    private final String arquivo = "contatos.txt";

    public Agenda() {
        contatos = new ArrayList<>();
        carregarContatos();
    }

    public void adicionarContato(String nome, String telefone) {
        if (nome.isBlank() || telefone.isBlank()) {
            System.out.println("❌ Nome ou telefone não podem estar vazios!");
            return;
        }
        if (!telefone.matches("[0-9()+\\- ]+")) {
            System.out.println("❌ Telefone inválido! Use apenas números, +, -, () ou espaço.");
            return;
        }
        for (Contato c : contatos) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                System.out.println("❌ Já existe um contato com esse nome!");
                return;
            }
        }
        contatos.add(new Contato(nome, telefone));
        salvarContatos();
        System.out.println("Contato adicionado com sucesso!");

    }

    public void listarContatos(){
        if(contatos.isEmpty()){
            System.out.println("Agenda Vazia");
        }else{
            System.out.println("\n📒 Lista de Contatos:");
            int i = 1;
            for (Contato c : contatos) {
                System.out.println(i + ". " + c);
                i++;
            }
        }
    }

    public void buscarContato(String nome) {
        for (Contato c : contatos) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                System.out.println("🔎 Encontrado: " + c);
                return;
            }
        }
        System.out.println("❌ Contato não encontrado!");
    }

    public void removerContato(String nome) {
        for (Contato c : contatos) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                contatos.remove(c);
                salvarContatos();
                System.out.println("🗑️ Contato removido com sucesso!");
                return;
            }
        }
        System.out.println("❌ Contato não encontrado para remover!");
    }

    public void editarContato(String nomeAntigo, String novoNome, String novoTelefone) {
        for (Contato c : contatos) {
            if (c.getNome().equalsIgnoreCase(nomeAntigo)) {
                if (!novoNome.isBlank()) {
                    c.setNome(novoNome);
                }
                if (!novoTelefone.isBlank()) {
                    if (novoTelefone.matches("[0-9()+\\- ]+")) {
                        c.setTelefone(novoTelefone);
                    } else {
                        System.out.println("❌ Telefone inválido!");
                        return;
                    }
                }
                salvarContatos();
                System.out.println("✏️ Contato atualizado com sucesso!");
                return;
            }
        }
        System.out.println("❌ Contato não encontrado!");
    }

    private void salvarContatos() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(arquivo))) {
            for (Contato c : contatos) {
                pw.println(c.getNome() + ":" + c.getTelefone());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar contatos: " + e.getMessage());
        }
    }

    private void carregarContatos() {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(":");
                if (dados.length == 2) {
                    contatos.add(new Contato(dados[0], dados[1]));
                }
            }
        } catch (IOException e) {
            // se não existir o arquivo, ignora
        }
    }
}