const API = "http://localhost:8080/usuario/me";


function authfetch(url, options = {}) {
    const token = localStorage.getItem("token");

    if (!token || token.value === "") {
        alert("Usuario não authenticado!!");
        return;
    }

    return fetch(url, {
        ...options,
        headers: {
            ...(options),
            "Authorization": `Bearer ${token}`,
            "Content-Type": "application/json"
        }
    })
}


async function verMeusDados() {
    const res = await authfetch(API);

    if (!res.ok) { alert("Ocorreu um erro ao buscar o usuario"); return; }

    const usuario = await res.json();

    let table = `<table border=1><tr><th>ID:</th><th>Email:</th><th>Role</th><th>Ações</th></tr>
    <tr>
        <td>${usuario.id}</td>
        <td>${usuario.email}</td>
        <td>${usuario.role}</td>
        <td>
            <button type="button" onclick="atualizarSenha()">Editar senha</button>
            <button type="button" onclick="deletarConta()">Deletar conta</button>
        </td>
    </tr>
    </table>`;

    document.getElementById("lista-opcoes").innerHTML = table;
}

async function atualizarSenha() {

    let form = `<form>
        <label>Senha Atual</label>
        <input type="password" id="senhaAtual"></input><br><br>

        <label>Nova Senha</label>
        <input type="password" id="novaSenha"></input><br><br>

        <button type="button" id="botaoAtualizar" onclick="atualizarSenhaAtual()">Atualizar senha</button>
    </form>`

    document.getElementById("lista-opcoes").innerHTML = form;

}

async function atualizarSenhaAtual() {

    const senha = {
        senhaAtual: document.getElementById("senhaAtual").value,
        novaSenha: document.getElementById("novaSenha").value
    }

    if (senha.senhaAtual === "" || senha.senhaAtual === "") { alert("Campos em branco!!"); return; }

    const res = await authfetch(`${API}/senha`, { body: JSON.stringify(senha), method: "PUT" });

    if (!res.ok || res.status !== 200) { alert("Ocorreu um erro ao atualizar a senha do usuario (Credenciais invalidas)"); return; }

    alert("Senha de Usuario Atualizada Com Sucesso, Redimencionando para Pagina de Login");
    document.getElementById("lista-opcoes").innerHTML = "";
    window.location.href = "../Login/login.html"
}

async function deletarConta() {
    if (!confirm("DESEJA DELETAR SUA CONTA ?")) { return; }

    const res = await authfetch(API, { method: "DELETE" });

    if (!res.ok || res.status !== 200) { alert("Ocorreu um erro ao deletar sua conta"); return; }

    alert("Conta Deletada com sucesso, redimencionando para a pagina de CADASTRO!");
    window.location.href = "../Cadastro/cadastro.html"

}


