const API = "http://localhost:8080/profissionais";
let idEdicao = null

document.getElementById("botaoCadastrar").disabled=true;

document.getElementById("nomeProfissionalId").addEventListener("input",verificarFormulario);
document.getElementById("emailId").addEventListener("input",verificarFormulario);
document.getElementById("senhaId").addEventListener("input", verificarFormulario)

function authFetch(url, options = {}) {
    const token = localStorage.getItem("token");

    if (!token) {
        alert("Profissional não autenticado");
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

document.getElementById("form-profissional").addEventListener("submit", async (pagina) => {
    pagina.preventDefault();

    const profissional = {
        nome: document.getElementById("nomeProfissionalId").value,
        email: document.getElementById("emailId").value,
        senha: document.getElementById("senhaId").value
    }

    const res=await authFetch(API,{
        method:"POST",
        body:JSON.stringify(profissional)
    });

    if(res.status!==201){
        alert("Ocorreu um erro ao cadastrar Profissional!!")
        return;
    }

    document.getElementById("form-profissional").reset();
    listarTodos()
    verificarFormulario();

});

async function listarTodos() {
    const res = await authFetch(API);

    if(res.status==403){alert("Você não possui permissão para acessar a lista de profissionais");return;}

    if(!res.ok || !res){alert("Houve um erro ao listar os Profissionais"); return;}

    const lista = await res.json();

    let html = "<table border=1><tr><th>ID:</th><th>Nome:</th><th>id do usuario:</th><th>email:</th><th>Role</th></tr>"

    lista.forEach(profissional => {
        html += `
        <tr>
            <td>${profissional.id}</td>
            <td>${profissional.nome}</td>
            <td>${profissional.usuario.id}</td>
            <td>${profissional.usuario.email}</td>
            <td>${profissional.usuario.role}</td>
        </tr>`
    });

    html += "</table>"
    document.getElementById("listaProfissionais").innerHTML = html;
}

async function editarProfissional(id) {
    const res = await fetch(`${API}/${id}`)

    if (!res.ok) { alert("Profissional não encontrado!!"); return };

    const profissional = await res.json();

    document.getElementById("nomeProfissionalId").value = profissional.nome;
    document.getElementById("ativoId").value = profissional.ativo;

    idEdicao = id;
    document.getElementById("botaoCadastrar").innerText = "Atualizar Profissional"

}

async function deletarProfissional(id) {
    if (!confirm("deseja deletar o Profissional Selecionado??")) return;

    const res = await fetch(`${API}/${id}`, { method: "DELETE" });

    if (!res.ok) { alert("Ocorreu um erro ao deletar o Profissional selecionado!!"); return; }

    alert("Profissional Deletado Com sucesso!!");
    listarTodos();
}

function verificarFormulario(){
    const campoNomeProfissional=document.getElementById("nomeProfissionalId").value
    const campoEmail=document.getElementById("emailId").value
    const campoSenha=document.getElementById("senhaId").value

    const botao=document.getElementById("botaoCadastrar")

    botao.disabled= campoNomeProfissional==="" || campoEmail==="" || campoSenha==="";

}

listarTodos();

