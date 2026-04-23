const API = "http://localhost:8080/treinos";
let idEdicao = null;


document.getElementById("form-treino").addEventListener("submit", async (pagina) => {
    pagina.preventDefault();

    const treino = {
        cliente: { id: Number(document.getElementById("clienteId").value) },
        dataInicio: document.getElementById("dataId").value,
        listaExercicios: [{ id: Number(document.getElementById("itemExercicioId").value) }]
    }

    if (idEdicao !== null) {
        await fetch(`${API}/${idEdicao}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(treino)
        });

        alert("Treino Atualizado com sucesso!!")
        idEdicao = null;
        document.querySelector("button[type='submit']").innetText = "Cadastrar Treino";

    } else {
        await fetch(API, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(treino)
        });

        alert("Treino Cadastrado com sucesso!!");


    }

    listarTodos();
    document.getElementById("form-treino").reset();


});

async function listarTodos() {
    const res = await fetch(API);
    const ListaTreinos = await res.json();

    let html = "<table><tr><th>ID:</th><th>Id Do Cliente:</th><th>Data De Inicio</th><th>Id do item Exercicio</th><th>Ações:</th></tr>";

    ListaTreinos.forEach(treino => {
        html += `
        <tr>
            <td>${treino.id}</td>
            <td>${treino.cliente.id}</td>
            <td>${treino.dataInicio}</td>
            <td>${treino.listaExercicios[0].id}</td>
            <td>
                <button class="botaoEditar" onclick="carregarTreino(${treino.id})">Editar</button>
                <button class="botaoDeletar" onclick="deletarTreino(${treino.id})">Deletar</button>
            </td>

        </tr>
        `
    });

    html += "</table>"
    document.getElementById("Lista De treinos").innerHTML = html;

};

async function carregarTreino(id) {
    const res = await fetch(`${API}/${id}`);
    const treino = await res.json();

    idEdicao = id;

    document.getElementById("clienteId").value = treino.cliente.id;
    document.getElementById("dataId").value = treino.dataInicio;
    document.getElementById("itemExercicioId").value = treino.listaExercicios[0].id;

    document.querySelector("button[type='submit']").innetText = "Atualizar Treino"


};

async function deletarTreino(id) {

    if (!confirm("deseja deletar esse treino??")) return;

    await fetch(`${API}/${id}`, { method: "DELETE" });

    alert("treino Deletado com sucesso!!");
    listarTodos();

}


listarTodos();

