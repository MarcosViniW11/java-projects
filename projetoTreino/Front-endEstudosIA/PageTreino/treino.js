const API = "http://localhost:8080/treinos";
let listaTemporaria = []

function AdicionarExercicio() {
    const series = document.getElementById("seriesId").value
    const repeticoes = document.getElementById("repsId").value
    const carga = document.getElementById("cargID").value
    const id = document.getElementById("exerID").value

    if (!series || !repeticoes || !carga || !id) {
        alert("preencha todos os campos para adicionar um exercicio");
        return;
    }

    const novoExercicio = {
        series: parseInt(series),
        repeticoes: parseInt(repeticoes),
        carga: carga,
        exercicio: { id: parseInt(id) }
    }

    listaTemporaria.push(novoExercicio)


    document.getElementById("seriesId").value = "";
    document.getElementById("repsId").value = "";
    document.getElementById("cargID").value = "";
    document.getElementById("exerID").value = "";

    mostrarListaTemporaria();
}

function mostrarListaTemporaria() {
    if (listaTemporaria.length === 0) {
        document.getElementById("listaExercicios").innerHTML = "<p class='empty'>Nenhum exercício adicionado.</p>";
        return;
    }

    let tableExercicios = "<div class='table-container'><table><thead><tr><th>Id</th><th>Séries</th><th>Repetições</th><th>Carga</th></tr></thead><tbody>";
    listaTemporaria.forEach(exercicioC => {
        tableExercicios += `
        <tr>
            <td>${exercicioC.exercicio.id}</td>
            <td>${exercicioC.series}</td>
            <td>${exercicioC.repeticoes}</td>
            <td>${exercicioC.carga}</td>
        </tr>`;
    });
    tableExercicios += "</tbody></table></div>";
    document.getElementById("listaExercicios").innerHTML = tableExercicios;
}


async function cadastrarTreinoCompleto() {

    const treino = {
        nome: document.getElementById("nomeTreinoId").value,
        usuario: { id: document.getElementById("userID").value },
        exerciciosRelacionados: listaTemporaria
    }

    const res = await fetch(API, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(treino)
    })

    if (!res.ok) {
        const retorno = await res.json();
        const mensagem = retorno.message || retorno.mensage || "Erro desconhecido";
        alert("OCORREU UM ERRO: " + mensagem);
        listaTemporaria = []
        document.getElementById("listaExercicios").innerHTML = "";
        return;
    }

    alert("Treino Cadastrado Com Sucesso")
    listaTemporaria = []
    document.getElementById("listaExercicios").innerHTML = "";
    document.getElementById("nomeTreinoId").value = ""
    document.getElementById("userID").value = ""
    window.location.href = "../PageUser/user.html";
}

async function buscarEmailPorID() {
    const email = document.getElementById("emailTxt").value

    const res = await fetch(`http://localhost:8080/usuarios/buscar/${email}`);

    if (!res.ok) {
        const retorno = await res.json();
        const mensagem = retorno.message || retorno.mensage || "Erro desconhecido";
        alert("OCORREU UM ERRO: " + mensagem);
        return;
    }

    const jsonR = await res.json();
    let id = jsonR.id
    alert(`ID de usuario ${id} encontrado para o email ${email}`);
    document.getElementById("userID").value = id;
    document.getElementById("emailTxt").value = "";
}

