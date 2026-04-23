const API = "http://localhost:8080/clientes";
let idEdicao = null;

const formCliente = document.getElementById("form-cliente");
const botaoCadastrar = document.getElementById("botaoCadastrar");

const nomeInput = document.getElementById("nomeId");
const telefoneInput = document.getElementById("telefoneId");
const emailInput = document.getElementById("emailId");
const senhaInput = document.getElementById("senhaId");

function authFetch(url, options = {}) {
    const token = localStorage.getItem("token");

    if (!token) {
        alert("Usuário não autenticado");
        window.location.href = "../Login/index.html";
        return;
    }

    return fetch(url, {
        ...options,
        headers: {
            ...(options.headers || {}),
            "Authorization": `Bearer ${token}`,
            "Content-Type": "application/json"
        }
    });
}

botaoCadastrar.disabled = true;

[nomeInput, telefoneInput, emailInput, senhaInput]
    .forEach(input => input.addEventListener("input", verificarFormulario));

formCliente.addEventListener("submit", async (e) => {
    e.preventDefault();

    const cliente = {
        nome: nomeInput.value,
        telefone: telefoneInput.value,
        email: emailInput.value,
        senha: senhaInput.value
    };

    let res;

    if (idEdicao !== null) {
        res = await authFetch(`${API}/${idEdicao}`, {
            method: "PUT",
            body: JSON.stringify(cliente)
        });
        idEdicao = null;
        botaoCadastrar.innerText = "Cadastrar Cliente";
    } else {
        res = await authFetch(API, {
            method: "POST",
            body: JSON.stringify(cliente)
        });
    }

    if (!res || !res.ok) {
        alert("Erro ao salvar cliente");
        return;
    }

    formCliente.reset();
    verificarFormulario();
    listarTodos();
});

async function listarTodos() {
    const res = await authFetch(API);

    if (res.status === 403) { alert("Você não possui o acesso para a lista de clientes"); return; }

    if (!res || !res.ok) { alert("Ocorreu um erro ao listar os clientes..."); return; }

    const clientes = await res.json();

    let table = `
    <table border="1">
        <tr>
            <th>ID</th><th>Nome</th><th>Telefone</th><th>id do usuario</th><th>email</th><th>role</th>
        </tr>`;

    clientes.forEach(c => {
        table += `
        <tr>
            <td>${c.id}</td>
            <td>${c.nome}</td>
            <td>${c.telefone}</td>
            <td>${c.usuario.id}</td>
            <td>${c.usuario.email}</td>
            <td>${c.usuario.role}</td>
        </tr>`;
    });

    table += "</table>";
    document.getElementById("tabela-Clientes").innerHTML = table;
}

async function editarCliente(id) {
    const res = await authFetch(`${API}/${id}`);
    const cliente = await res.json();

    nomeInput.value = cliente.nome;
    telefoneInput.value = cliente.telefone;

    idEdicao = id;
    botaoCadastrar.innerText = "Atualizar Cliente";
}

async function deletarCliente(id) {
    if (!confirm("Deseja realmente deletar?")) return;

    const res = await authFetch(`${API}/${id}`, { method: "DELETE" });
    if (!res.ok) {
        alert("Erro ao deletar");
        return;
    }

    listarTodos();
}

function verificarFormulario() {
    botaoCadastrar.disabled =
        !nomeInput.value ||
        !telefoneInput.value ||
        !emailInput.value ||
        !senhaInput.value;
}

listarTodos();
