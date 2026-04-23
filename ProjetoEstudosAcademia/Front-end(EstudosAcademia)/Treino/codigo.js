const API = "http://localhost:8080/treinos";
let idEdicao = null;

let listaExercicios = [];

let indexItemEdicao = null;

function verificarFormulario() {
    const campoIdCliente = document.getElementById("clienteId").value;
    const botao = document.getElementById("CadastrarTreino");

    botao.disabled = campoIdCliente === "" || listaExercicios.length === 0;
}

document.getElementById("clienteId").addEventListener("input", verificarFormulario);

function carregarExercicioNoTreino() {
    const itemExercicio = {
        exercicio: { id: Number(document.getElementById("exercicioId").value) },
        series: Number(document.getElementById("seriesId").value),
        repeticoes: Number(document.getElementById("repeticoesId").value),
        tempoDescanco: Number(document.getElementById("tempoDescancoId").value)
    };

    if (indexItemEdicao !== null) {
        listaExercicios[indexItemEdicao] = itemExercicio;
        document.getElementById("botaoCadastrarExercicio").innerText = "Atualizar Item";
    } else {
        listaExercicios.push(itemExercicio);
    }

    document.getElementById("exercicioId").value = "";
    document.getElementById("seriesId").value = "";
    document.getElementById("repeticoesId").value = "";
    document.getElementById("tempoDescancoId").value = "";

    indexItemEdicao = null;
    document.getElementById("botaoCadastrarExercicio").innerText = "Cadastrar exercicio a lista treino:"

    mostrarLista();
    verificarFormulario();

};

function mostrarLista() {
    let lista = "<table><tr><th>ID Exercicio:</th><th>Series:</th><th>Repetições</th><th>tempoDescanço</th><th>Ação</th></tr>"

    listaExercicios.forEach(itemExer => {
        lista += `
        <tr>
            <td>${itemExer.exercicio.id}</td>
            <td>${itemExer.series}</td>
            <td>${itemExer.repeticoes}</td>
            <td>${itemExer.tempoDescanco}</td>
            <td>
                <button id="botaoDeletarLista" type="button" onclick="DeletarItemExercicio(${itemExer.exercicio.id})">Deletar</button>
                <button id="botaoEditarLista" type="button" onclick="EditarItemExercicio(${itemExer.exercicio.id})">Editar</button>
            </td>
        </tr>
        `
    });

    lista += "</table>"
    document.getElementById("listaExercicioCada").innerHTML = lista;
};

function DeletarItemExercicio(idExercicio) {
    listaExercicios = listaExercicios.filter(item =>
        item.exercicio.id !== idExercicio
    );

    mostrarLista();
    verificarFormulario();
}

function EditarItemExercicio(idExercicio) {
    const index = listaExercicios.findIndex(item =>         // esse findIndex é basicamente um forEach com uma condição, nesse caso o id do exercicio, quando ele acha ele retorna o objeto inteiro por isso podemos buscar na lista com o listaExercicios[index] e coloca-lo no formulario.
        item.exercicio.id === idExercicio
    );

    if (index === -1) return;

    const item = listaExercicios[index];

    document.getElementById("exercicioId").value = item.exercicio.id;
    document.getElementById("seriesId").value = item.series;
    document.getElementById("repeticoesId").value = item.repeticoes;
    document.getElementById("tempoDescancoId").value = item.tempoDescanco;

    //document.querySelector("button[type='button']").innerText = "Atualizar itemExercicio"
    document.getElementById("botaoCadastrarExercicio").innerText = "Atualizar Item"

    indexItemEdicao = index;
}

async function ListarTreinoId() {
    const id = document.getElementById("treinoId").value
    const res = await fetch(`${API}/${id}`)

    if (!res.ok) {
        alert("treino não encontrado !!")
        return;
    }

    const treino = await res.json();

    let html = `
        <h3>Treino #${treino.id}</h3>
        <p>Cliente: ${treino.cliente.nome}</p>
        <p>Data De Inicio: ${treino.dataInicio}</p>
        <p>Status Do Treino: ${treino.status}</p>

        <button type="button" class="botaoDeletarTreino" onclick="DeletarTreino(${treino.id})">Deletar Treino</button>
        <button type="button" class="botaoEditarTreino" onclick="EditarTreino(${treino.id})">Editar Treino</button>
        <br>
        <br>
        <table border="1">
            <tr>
                <th>Exercicio:</th>
                <th>Series:</th>
                <th>repeticoes:</th>
                <th>tempo De Descanço</th>
            </tr>
    `

    treino.listaExercicios.forEach(item => {
        html += `
            <tr>
                <td>${item.exercicio.nomeExercicio}</td>
                <td>${item.series}</td>
                <td>${item.repeticoes}</td>
                <td>${item.tempoDescanco}</td>
            </tr>
        `

    });


    html += "</table>"
    document.getElementById("ListaTreinoId").innerHTML = html;

};

document.getElementById("statusId").disabled = true;

async function EditarTreino(id) {
    const res = await fetch(`${API}/${id}`);
    const treino = await res.json();
    document.getElementById("statusId").disabled = false;

    document.getElementById("clienteId").value = treino.cliente.id;
    document.getElementById("dataId").value = treino.dataInicio;
    document.getElementById("statusId").value = treino.status;

    listaExercicios = []

    treino.listaExercicios.forEach(itemExercicio => {
        listaExercicios.push(itemExercicio);
    });

    idEdicao = id;
    mostrarLista();
    document.getElementById("CadastrarTreino").innerText = "Atualizar Treino"
    document.getElementById("ListaTreinoId").innerHTML = "";

}

async function DeletarTreino(id) {

    if (!confirm("Deseja Realmente Deletar o treino Listado??")) return;

    const res = await fetch(`${API}/${id}`, { method: "DELETE" });

    if (!res.ok) {
        alert("Erro ao Deletar Treino!!");
        return;
    }

    alert("Treino Deletado Com sucesso!!");

    document.getElementById("ListaTreinoId").innerHTML = ""



};


document.getElementById("form-treino").addEventListener("submit", async (pagina) => {
    pagina.preventDefault();

    const treino = {
        cliente: { id: Number(document.getElementById("clienteId").value) },
        dataInicio: document.getElementById("dataId").value,
        listaExercicios: listaExercicios
    }

    if (!treino.cliente.id || isNaN(treino.cliente.id)) { alert("Id do cliente Invalido"); return; }

    if (treino.listaExercicios.length < 1) {
        alert("Lista De Exercicio tem que tem ao menos UM exercicio!!");
        return;
    }

    if (idEdicao !== null) {

        treino.status = document.getElementById("statusId").value

        const res = await fetch(`${API}/${idEdicao}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(treino)
        });

        if (!res.ok) {
            const erro = await res.text();
            alert(erro);
            return;
        }

        alert("Treino Atualizado com sucesso!!")
        idEdicao = null;
        document.getElementById("CadastrarTreino").innerText = "Cadastrar Treino";
        document.getElementById("statusId").value = "";
        document.getElementById("statusId").disabled = true;

    } else {

        const res = await fetch(API, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(treino)
        });

        if (!res.ok) {
            const erro = await res.text();
            alert(erro);
            return;
        }

        alert("Treino Cadastrado com sucesso!!");
    }

    document.getElementById("form-treino").reset();
    listaExercicios = [];
    mostrarLista();


});