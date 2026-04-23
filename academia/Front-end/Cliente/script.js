const API = "http://localhost:8080/clientes";
let idEdicao = null;

document.getElementById("form-cliente").addEventListener("submit", async (pagina) => {
    pagina.preventDefault();

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
        document.querySelector("button[type='submit']").innerText = "Cadastrar Cliente"
    } else {
        await fetch(API, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(cliente)
        });

        alert("Cliente Cadastrado Com sucesso");

    }
    document.getElementById("form-cliente").reset();
    listarTodos();
});

async function listarTodos() {
    const res = await fetch(API);
    const ListaCliente = await res.json();

    let html = "<table><tr><th>ID:</th><th>Nome:</th><th>Email</th><th>Telefone</th><th>Ações</th></tr>";

    ListaCliente.forEach(cliente => {
        html += `
        <tr>
            <td>${cliente.id}</td>
            <td>${cliente.nome}</td>
            <td>${cliente.email}</td>
            <td>${cliente.telefone}</td>
            <td>
                <button class="botaoEditar" onclick="carregarCliente(${cliente.id})"> Editar </button>
                <button class="botaoDeletar" onclick="deletarCliente(${cliente.id})"> Excluir </button>
            </td>
        </tr>
        `
    });

    html += "</table>"

    document.getElementById("listaClientes").innerHTML = html;

};

async function carregarCliente(id) {

    const res = await fetch(`${API}/${id}`);
    const cliente = await res.json();

    document.getElementById("nomeId").value = cliente.nome;
    document.getElementById("emailId").value = cliente.email;
    document.getElementById("telefoneId").value = cliente.telefone;

    idEdicao = id;

    document.querySelector("button[type='submit']").innerText = "Atualizar Cliente";

};

async function deletarCliente(id) {
    if (!confirm("Deseja deletar esse Cliente?")) return;

    await fetch(`${API}/${id}`, { method: "DELETE" });

    alert("Cliente Deletado!!")

    listarTodos();
};


listarTodos();




