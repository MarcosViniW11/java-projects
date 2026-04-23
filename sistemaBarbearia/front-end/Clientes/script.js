const API = "http://localhost:8080/clientes";
let idEdicao = null

document.getElementById("botaoCadastrar").disabled = true;

document.getElementById("nomeId").addEventListener("input", verificarFormulario)
document.getElementById("telefoneId").addEventListener("input", verificarFormulario)


document.getElementById("form-cliente").addEventListener("submit", async (pagina) => {
    pagina.preventDefault();


    const cliente = {
        nome: document.getElementById("nomeId").value,
        telefone: document.getElementById("telefoneId").value
    }

    if (idEdicao !== null) {
        const res = await fetch(`${API}/${idEdicao}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(cliente)
        });

        if (!res.ok) {
            alert("houve um erro ao atualizar o Cliente selecionado!!");
            return;
        }

        alert("Cliente atualizado com sucesso!");
        idEdicao = null
        document.getElementById("botaoCadastrar").innerText = "Cadastrar Cliente"
    } else {
        const res = await fetch(API, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(cliente)
        })

        if (!res.ok) {
            alert("ocorreu um erro ao cadastrar o cliente")
            return
        }

        alert("Cliente Cadastrado Com sucesso!!");

    }

    listarTodos();
    document.getElementById("form-cliente").reset();
    verificarFormulario();


});


async function listarTodos() {
    const res = await fetch(API);
    const clintes = await res.json();

    let table = "<table border=1><tr><th>ID:</th><th>Nome</th><th>Telefone:</th><th>Ações</th></tr>"

    clintes.forEach(cliente => {
        table += `
        <tr>
            <td>${cliente.id}</td>
            <td>${cliente.nome}</td>
            <td>${cliente.telefone}</td>
            <td>
                <button type="button" id="botaoEditar" onclick="editarCliente(${cliente.id})">Editar</button>
                <button type="button" id="botaoDeletar" onclick="deletarCliente(${cliente.id})">Deletar</button>
            </td>
        </tr>
        `
    })

    table += "</table>"
    document.getElementById("tabela-Clientes").innerHTML = table;

}

async function editarCliente(id) {
    const res = await fetch(`${API}/${id}`)
    const cliente = await res.json();

    document.getElementById("nomeId").value = cliente.nome;
    document.getElementById("telefoneId").value = cliente.telefone;

    idEdicao = id;

    document.getElementById("botaoCadastrar").innerText = "Atualizar Cliente"

}

async function deletarCliente(id) {
    if (!confirm("deseja Realmente deletar o Cliente selecionado??")) return;

    const res = await fetch(`${API}/${id}`, { method: "DELETE" })

    if (!res.ok) {
        alert("Houve um erro ao deletar o cliente");
        return;
    }

    alert("Cliente deletado Com sucesso!!")

    listarTodos();
}

function verificarFormulario() {
    const campoNome = document.getElementById("nomeId").value
    const campoTelefone = document.getElementById("telefoneId").value

    const botao = document.getElementById("botaoCadastrar")

    botao.disabled = campoNome === "" || campoTelefone === "";

}


listarTodos();

