const API = "http://localhost:8080/produtos";
let idEdicao = null

document.getElementById("form-produto").addEventListener("submit", async (pagina) => {
    pagina.preventDefault();

    const produto = {
        nome: document.getElementById("nomeProdutoId").value,
        quantidadeEstoque: Number(document.getElementById("qtbEstoqueId").value),
        valorUnitario: Number(document.getElementById("valorUnitarioId").value)
    };

    if (idEdicao !== null) {
        const res = await fetch(`${API}/${idEdicao}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(produto)
        });

        if (!res.ok) {
            alert("ERROR ao atualizar o produto selecionado!!");
            return;
        }

        alert("Produto Atualizado Com sucesso!!");
        document.getElementById("botaoCadastrarProdutoId").innerText = "Cadastrar Produto";
    } else {
        const res = await fetch(API, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(produto)
        });

        if (!res.ok) {
            alert("Ococrreu um erro ao cadastrar o Produto!!")
            return;
        }

        alert("Produto Cadastrado Com sucesso!!")

    }

    document.getElementById("form-produto").reset();
    listarTodos();


});

async function listarTodos() {
    const res = await fetch(API)
    const listaProdutos = await res.json();

    let html = "<table border=1><tr><th>ID:</th><th>Nome:</th><th>Qtb Estoque:</th><th>Valor Unitario</th><th>Ações</th></tr>"

    listaProdutos.forEach(produto => {
        html += `
        <tr>
            <td>${produto.id}</td>
            <td>${produto.nome}</td>
            <td>${produto.quantidadeEstoque}</td>
            <td>${produto.valorUnitario}</td>
            <td>
                <button type="button" id="botaoEditar" onclick="EditarProduto(${produto.id})">Editar</button>
                <button type="button" id="botaoDeletar" onclick="DeletarProduto(${produto.id})">Deletar</button>
            </td>
        </tr>
        `
    })

    html += "</table>"
    document.getElementById("lista-produtos").innerHTML = html;

}

async function EditarProduto(id) {
    const res = await fetch(`${API}/${id}`);

    if (!res.ok) { alert("Produto não encontrado!!"); return; }

    const produto = await res.json();



    document.getElementById("nomeProdutoId").value = produto.nome;
    document.getElementById("qtbEstoqueId").value = produto.quantidadeEstoque;
    document.getElementById("valorUnitarioId").value = produto.valorUnitario;

    idEdicao = id;
    document.getElementById("botaoCadastrarProdutoId").innerText = "Atualizar Produto"

};

async function DeletarProduto(id) {

    if (!confirm("Deseja Deletar o Produto selecionado!!")) return;

    const res = await fetch(`${API}/${id}`, { method: "DELETE" });

    if (!res.ok) {
        alert("Ocorreu um erro ao deletar o produto selecionado!!");
        return;
    }

    alert("Produto Deletado Com sucesso!!")
    listarTodos();

};


listarTodos();


