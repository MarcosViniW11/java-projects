const API = "http://localhost:8080/profissionais";
let idEdicao = null

document.getElementById("botaoCadastrar").disabled=true;

document.getElementById("nomeProfissionalId").addEventListener("input",verificarFormulario);
document.getElementById("ativoId").addEventListener("input",verificarFormulario);

document.getElementById("form-profissional").addEventListener("submit", async (pagina) => {
    pagina.preventDefault();

    const profissional = {
        nome: document.getElementById("nomeProfissionalId").value,
        ativo: document.getElementById("ativoId").value
    }

    if (idEdicao !== null) {
        const res = await fetch(`${API}/${idEdicao}`, {
            method: "PUT",
            headers: { "Content-type": "application/json" },
            body: JSON.stringify(profissional)
        });

        if (!res.ok) { alert("Ocorreu um erro ao atulizar o Profissional selecionado!!"); return };

        alert("Profissional Atualizado com sucesso!!");
        idEdicao = null
        document.getElementById("botaoCadastrar").innerText="Cadastrar Profissional";
    } else {
        const res = await fetch(API, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(profissional)
        });

        if (!res.ok) { alert("Houve um erro ao cadastrar o Profissional"); return; }

        alert("Profissional Cadastrado Com sucesso!!")
    }

    document.getElementById("form-profissional").reset();
    listarTodos();
    verificarFormulario();

});

async function listarTodos() {
    const res = await fetch(API);
    const lista = await res.json();

    let html = "<table border=1><tr><th>ID:</th><th>Nome:</th><th>Ativo:</th><th>Ações</th></tr>"

    lista.forEach(profissional => {
        html += `
        <tr>
            <td>${profissional.id}</td>
            <td>${profissional.nome}</td>
            <td>${profissional.ativo}</td>
            <td>
                <button type="button" id="botaoEditar" onclick="editarProfissional(${profissional.id})">Editar</button>
                <button type="button" id="botaoDeletar" onclick="deletarProfissional(${profissional.id})">Excluir</button>
            </td>
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
    const campoAtvo=document.getElementById("ativoId").value

    const botao=document.getElementById("botaoCadastrar")

    botao.disabled= campoNomeProfissional==="" || campoAtvo===""

}

listarTodos();

