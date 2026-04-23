const API = "http://localhost:8080/clientes";
let idEdicao = null;


document.getElementById("form-cadastro").addEventListener("submit", async (pagina) => {
    pagina.preventDefault();

    const cliente = {
        nome: document.getElementById("nomeId").value,
        email: document.getElementById("emailId").value,
        cpf: document.getElementById("cpfId").value,
        telefone: document.getElementById("telefoneId").value,
        endereco: document.getElementById("enderecoId").value
    };

    if (idEdicao !== null) {
        await fetch(`${API}/${idEdicao}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(cliente)
        });

        alert("Cliente atualizado com sucesso!!");

        idEdicao = null;
        document.querySelector("button[type='submit']").innerText = "Cadastrar";

    } else {
        await fetch(API, {
            method: "Post",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(cliente)
        });

        alert("Cliente cadastrado!!");

    }

    document.getElementById("form-cadastro").reset();
    listarTodos();


})

async function listarTodos() {
    const res = await fetch(API);
    const lista = await res.json();

    let html = "<table><tr><th>ID</th><th>Nome:</th><th>Email:</th><th>CPF:</th><th>Telefone:</th><th>Endereço:<th>Ações:</th></th></tr>";

    lista.forEach(c => {
        html += `
        <tr>
            <td>${c.id}</td>
            <td>${c.nome}</td>
            <td>${c.email}</td>
            <td>${c.cpf}</td>
            <td>${c.telefone}</td>
            <td>${c.endereco}</td>

            <td>
                <button class="editar" onclick="carregarCliente(${c.id})">Editar</button>    
                <button class="excluir" onclick="deletarCliente(${c.id})">Excluir</button>
            </td>
        </tr>`

    });

    html += "</table>";

    document.getElementById("listaCliente").innerHTML = html;

}

async function carregarCliente(id) {
    const res = await fetch(`${API}/${id}`);
    const cliente = await res.json();

    idEdicao = id;

    document.getElementById("nomeId").value = cliente.nome;
    document.getElementById("emailId").value = cliente.email;
    document.getElementById("cpfId").value = cliente.cpf;
    document.getElementById("telefoneId").value = cliente.telefone;
    document.getElementById("enderecoId").value = cliente.endereco;

    document.querySelector("button[type='submit']").innerText = "Atualizar";

}


async function deletarCliente(id) {
    if (!confirm("Deseja deletar este cliente?")) return;

    await fetch(`${API}/${id}`, { method: "DELETE" });

    alert("Cliente deletado com sucesso!!");
    listarTodos();

}






listarTodos();

