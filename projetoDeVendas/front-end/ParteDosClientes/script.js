const API = "http://localhost:8080/clientes";
let idEdicao = null;

document.getElementById("form-Cliente").addEventListener("submit", async (p) => {
    p.preventDefault();

    const cliente = {
        nome: document.getElementById("nomeId").value,
        email: document.getElementById("emailId").value,
        telefone: document.getElementById("telefoneId").value
    }

    if (idEdicao !== null) {
        await fetch(`${API}/${idEdicao}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(cliente)
        });

        alert("Cliente Atualizado");

        idEdicao = null;
        document.querySelector("button[type='submit']").innerText = "Cadastrar";
    } else {
        await fetch(API, {
            method: "POST",
            headers: { "Content-type": "application/json" },
            body: JSON.stringify(cliente)
        });

        alert("Cliente Cadastrado");
    }

    document.getElementById("form-Cliente").reset();
    listarTodos();



});


async function listarTodos() {
    const res = await fetch(API);
    const lista = await res.json();

    let html = `<table class="tabela-clientes"><tr><th>ID:</th><th>Nome:</th><th>Email:</th><th>Telefone:</th><th>Ações:</th></tr>`;

    lista.forEach(cliente => {
        html += `
        <tr>
            <td>${cliente.id}</td>
            <td>${cliente.nome}</td>
            <td>${cliente.email}</td>
            <td>${cliente.telefone}</td>

            <td>
                <button class="editar" onclick="carregarCliente(${cliente.id})">Editar</button>
                <button class="excluir" onclick="deletarCliente(${cliente.id})">Deletar</button>
            </td>

        </tr>`

    });
    html += "</table>"

    document.getElementById("ListaCliente").innerHTML = html;
}

async function carregarCliente(id) {
    const res = await fetch(`${API}/${id}`);
    const cliente = await res.json();

    idEdicao = id;

    document.getElementById("nomeId").value = cliente.nome;
    document.getElementById("emailId").value = cliente.email;
    document.getElementById("telefoneId").value = cliente.telefone;

    document.querySelector("button[type='submit']").innerText = "Atualizar"


}

async function deletarCliente(id) {
    if (!confirm("Deseja deletar o cliente selecionado?")) return;

    await fetch(`${API}/${id}`, { method: "DELETE" });

    alert("Cliente deletado Com Sucesso!")
    listarTodos();
}



listarTodos();