const API = "http://localhost:8080/clientes"
let idEdicao = null

const form = document.getElementById("form-cliente");
const botao = document.getElementById("botaoCadastrar");

const nome = document.getElementById("nomeId")
const telefone = document.getElementById("telefoneId")
const email = document.getElementById("emailId")
const senha = document.getElementById("senhaId")

nome.addEventListener("input", verificarFormulario)
telefone.addEventListener("input", verificarFormulario)
email.addEventListener("input", verificarFormulario)
senha.addEventListener("input", verificarFormulario)

botao.disabled = true;

function authfetch(url, options = {}) {
    const token = localStorage.getItem("token");

    if (!token) {
        alert("usuario não autenticado!!");
        window.location.href = "../Login/index.html";
        return;
    }

    return fetch(url, {
        ...options,
        headers: {
            ...(options.headers || {}),
            "Authorization": `Bearer ${token}`,
            "Content-type": "application/json"
        }
    });
}

form.addEventListener("submit", async (pagina) => {
    pagina.preventDefault();

    const cliente = {
        nome: nome.value,
        telefone: telefone.value,
        email: email.value,
        senha: senha.value
    };

    let res;

    if (idEdicao !== null) {
        res = await authfetch(`${API}/${idEdicao}`, {
            method: "PUT",
            body: JSON.stringify(cliente)
        });

        if (!res.ok || !res) { alert("Ocorreu um erro ao atualizar o cliente selecionado!!"); return; }

        alert("Cliente atualizado Com sucesso!!");
        idEdicao = null;
        botao.innerText = "Cadastrar"

    } else {
        res = await authfetch(API, {
            method: "POST",
            body: JSON.stringify(cliente)
        });

        if (!res || !res.ok) {
            alert("Erro Ao Cadastrar o Cliente");
            return;
        }
        alert("Cliente Cadastrado Com sucesso!!")
    }


    listar()
    form.reset();

});

async function listar() {
    const res = await authfetch((`${API}/listar`));

    if (!res.ok || !res) { alert("Ocorreu um erro ao listar, ou você não possui token de admin para isso!!"); return; }

    const listaCliente = await res.json();

    let table = "<table border=1><tr><th>ID</th><th>Nome:</th><th>Telefone:</th><th>Email:</th><th>Ações</th></tr>"

    listaCliente.forEach(cliente => {
        table += `
        <tr>
            <td>${cliente.id}</td>
            <td>${cliente.nome}</td>
            <td>${cliente.telefone}</td>
            <td>${cliente.email}</td>
            <td>
                <button type="button" onclick="Editar(${cliente.id})">Editar</button>
                <button type="button" onclick="Deletar(${cliente.id})">Deletar</button
            </td>
        </tr>
        `
    });

    table += "</table>"
    document.getElementById("ListaClientes").innerHTML = table;

}

async function Editar(id) {
    const res = await authfetch(`${API}/${id}`);

    if (!res.ok || !res) { alert("Ocorreu um erro ao buscar pelo Cliente selecionado!!"); return; }

    const cliente = await res.json()

    nome.value = cliente.nome;
    telefone.value = cliente.telefone;
    email.value = cliente.usuario.email;
    senha.value = "";

    idEdicao = id;

    botao.innerText = "atualizar Cadastro"

}

async function Deletar(id) {

    if (!confirm("Deseja realmente deletar o Cliente selecionado??")) { return; }

    const res = await authfetch(`${API}/${id}`, {
        method: "DELETE"
    });

    if (!res.ok || !res) { alert("ocorreu um erro ao deletar o Cliente selecionado!!"); return; }

    alert("Cliente Deletado com sucesso!!");

    listar();

}

listar();

//verificação de formulario

function verificarFormulario() {
    nomeInput = nome.value;
    telefoneInput = telefone.value;
    emailInput = email.value;
    senhaInput = senha.value;

    botao.disabled = nomeInput === "" || telefoneInput === "" || emailInput === "" || senhaInput === "";

}

