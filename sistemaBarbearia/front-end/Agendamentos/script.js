const API = "http://localhost:8080/agendamentos"
let idEdicao = null;

document.getElementById("botao-Cadastrar").disabled = true;
document.getElementById("botaoBuscarAgendamento").disabled = true;

document.getElementById("clienteId").addEventListener("input", verificarFormulario);
document.getElementById("profissionalId").addEventListener("input", verificarFormulario);
document.getElementById("servicoId").addEventListener("input", verificarFormulario);
document.getElementById("dataId").addEventListener("input", verificarFormulario);

document.getElementById("agendamentoId").addEventListener("input", verificarBuscaDeAgendamento)

document.getElementById("form-agendamento").addEventListener("submit", async (pagina) => {
    pagina.preventDefault();

    const agendamento = {
        clienteId: Number(document.getElementById("clienteId").value),
        profissionalId: Number(document.getElementById("profissionalId").value),
        servicoId: Number(document.getElementById("servicoId").value),
        dataHoraInicio: document.getElementById("dataId").value
    }

    if (idEdicao !== null) {
        const res = await fetch(`${API}/${idEdicao}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(agendamento)
        });

        if (!res.ok) {
            alert("Não foi possivel atualizar o agendamento selecionado")
            return;
        }

        alert("Agendamento atualizado com sucesso!!")
        idEdicao = null
        document.getElementById("botao-Cadastrar").innerText = "Cadastrar Agendamento";
    } else {
        const res = await fetch(API, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(agendamento)
        })

        if (!res.ok) {
            alert("Houve um erro ao cadastrar O Agendamento")
            return;
        }

        alert("Agendamento Cadastrado!!")

    };

    document.getElementById("form-agendamento").reset();
    verificarFormulario();


});

async function buscarAgendamento() {
    id = document.getElementById("agendamentoId").value;

    const res = await fetch(`${API}/${id}`);

    if (!res.ok) { alert("Agendamento Não Encontrado!!"); return; }
    const agendamento = await res.json();

    html = `
        <h3>Agendamento #${id}</h3>
        <p>Nome Do Cliente: ${agendamento.cliente.nome}</p>
        <p>Telefone: ${agendamento.cliente.telefone}</p>
        <p>Id: ${agendamento.cliente.id}</p>

        <table border=1><tr><th>Id do Profissional</th><th>Nome:</th><th>Ativo:</th><th>Id do Serviço</th><th>Nome:</th><th>Duração(minutos)</th><th>Preço:</th><th>Data e Hora de Inicio</th><th>Data e Hora de Fim</th><th>Status:</th><th>Ações:</th></tr>
    `

    html += `
    <tr>
        <td>${agendamento.profissional.id}</td>
        <td>${agendamento.profissional.nome}</td>
        <td>${agendamento.profissional.ativo}</td>
        <td>${agendamento.servico.id}</td>
        <td>${agendamento.servico.nome}</td>
        <td>${agendamento.servico.duracaoMinutos}</td>
        <td>${agendamento.servico.preco}</td>
        <td>${agendamento.dataHoraInicio}</td>
        <td>${agendamento.dataHoraFim}</td>
        <td>${agendamento.status}</td>
        <td>
            <button type="button" onclick="EditarAgendamento(${agendamento.id})">Editar</button>
            <button type="button" onclick="DeletarAgendamento(${agendamento.id})">Deletar</button>
        </td>
    </tr>
    `

    html += "</table>"

    document.getElementById("lista-agendamentos").innerHTML = html;

};

async function EditarAgendamento(id) {
    const res = await fetch(`${API}/${id}`)
    const agendamento = await res.json();

    document.getElementById("clienteId").value = agendamento.cliente.id;
    document.getElementById("profissionalId").value = agendamento.profissional.id;
    document.getElementById("servicoId").value = agendamento.servico.id;
    document.getElementById("dataId").value = agendamento.dataHoraInicio;

    document.getElementById("botao-Cadastrar").innerText = "Atualizar agendamento";
    document.getElementById("lista-agendamentos").innerHTML = "";

    idEdicao = id;

}

async function DeletarAgendamento(id) {

    if (!confirm("Deseja realemente deletar o Agendamento selecionado??")) return;

    const res = await fetch(`${API}/${id}`, { method: "DELETE" });

    if (!res.ok) {
        alert("Ocorreu um erro ao deletar o Agendamento selecionado!!"); return;
    }

    alert("Agendamento Deletado Com sucesso!!");

    document.getElementById("lista-agendamentos").innerHTML = "";

}

function verificarFormulario() {
    const campoIdCliente = document.getElementById("clienteId").value;
    const campoIdProfissional = document.getElementById("profissionalId").value;
    const campoIdServico = document.getElementById("servicoId").value;
    const dataAgendamento = document.getElementById("dataId").value;

    const botao = document.getElementById("botao-Cadastrar")

    botao.disabled = campoIdCliente === "" || campoIdProfissional === "" || campoIdServico === "" || dataAgendamento === "";

}

function verificarBuscaDeAgendamento() {
    const campoBuscar = document.getElementById("agendamentoId").value;
    const botaoBuscar = document.getElementById("botaoBuscarAgendamento")

    botaoBuscar.disabled = campoBuscar === "";

}




