const API = "http://localhost:8080/produtos";
let idEdicao = null;

document.getElementById("form-Produto").addEventListener("submit", async (pagina) => {
    pagina.preventDefault();

    const produto = {
        nome: document.getElementById("nomeId").value,
        precoUnitario: Number(document.getElementById("precoId").value),
        quantidadeEstoque: Number(document.getElementById("quantidadeId").value)
    };

    if (idEdicao != null) {
        const res = await fetch(`${API}/${idEdicao}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(produto)
        });

        alert("Produto Atualizado");
        idEdicao = null;
        document.querySelector("button[type=submit]").innerText = "Atualizar"

    } else {
        await fetch(API, {
            method: "POST",
            headers: { "Content-type": "application/json" },
            body: JSON.stringify(produto)
        });

        alert("Produto Cadastrado!!")

    }

    document.getElementById("form-Produto").reset();
    listarTodos();

});

async function listarTodos() {
    const res = await fetch(API);
    const produtos = await res.json();

    let html = `<table class="produtos"><tr><th>ID:</th><th>Nome:</th><th>Preço Unitario:</th><th>Qtb Estoque:</th><th>Ações:<th></tr>`;

    produtos.forEach(produto => {
        html += `
        <tr>
            <td>${produto.id}</td>
            <td>${produto.nome}</td>
            <td>${produto.precoUnitario}</td>
            <td>${produto.quantidadeEstoque}</td>

            <td>
                <button type="submit" class="botaoAtualizar" onclick="carregarProduto(${produto.id})">Atualizar</button>
                <button type="submit" class="botaoDeletar" onclick="deletarProduto(${produto.id})">Excluir</button>
            </td>

        </tr>`
    });

    html += "</table>";
    document.getElementById("listaProdutos").innerHTML = html;

}


async function carregarProduto(id) {
    const res = await fetch(`${API}/${id}`);
    const produto = await res.json();

    idEdicao = id

    document.getElementById("nomeId").value = produto.nome;
    document.getElementById("precoId").value = produto.precoUnitario;
    document.getElementById("quantidadeId").value = produto.quantidadeEstoque;

    document.querySelector("button[type='submit']").innerText = "Atualizar"

    listarTodos();

}


async function deletarProduto(id) {
    if (!confirm("deseja Deletar este produto??")) return;

    await fetch(`${API}/${id}`, { method: "DELETE" });

    alert("Produto deletado com sucesso!!");

    listarTodos();

}

listarTodos();