const API = "http://localhost:8080/exercicios";
let idEdicao = null;


document.getElementById("form-Exercicio").addEventListener("submit", async (pagina) => {
    pagina.preventDefault();

    const exercicio = {
        nomeExercicio: document.getElementById("nomeExercicioId").value,
        descricao: document.getElementById("descricaoId").value
    }

    if (idEdicao !== null) {
        await fetch(`${API}/${idEdicao}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(exercicio)
        });

        alert("Exercicio Atualizado!!")

        idEdicao = null;
        document.querySelector("button[type='submit']").innerText = "Cadastrar Exercicio";

    } else {
        await fetch(API, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(exercicio)
        });

        alert("Exercicio Cadastrado!!")

    }

    document.getElementById("form-Exercicio").reset();
    listarTodos();


});

async function listarTodos() {
    const res = await fetch(API);
    const lista = await res.json();

    let html = "<table><tr><th>ID:</th><th>Nome Do Exercicio:</th><th>Descrição:</th><th>Ações:</th></tr>";

    lista.forEach(exercicio => {
        html += `
        <tr>
            <td>${exercicio.id}</td>
            <td>${exercicio.nomeExercicio}</td>
            <td>${exercicio.descricao}</td>
            <td>
                <button class="botaoEditar" onclick="carregarExercicio(${exercicio.id})">Editar</button>
                <button class="botaoDeletar" onclick="deletarExercicio(${exercicio.id})">Deletar</button>
            </td>
        </tr>`

    });

    html += "</table>"
    document.getElementById("ListaExercicios").innerHTML = html;

};

async function carregarExercicio(id) {
    const res = await fetch(`${API}/${id}`);
    const exercicio = await res.json();

    document.getElementById("nomeExercicioId").value = exercicio.nomeExercicio;
    document.getElementById("descricaoId").value = exercicio.descricao;

    idEdicao = id;
    document.querySelector("button[type='submit']").innerText = "Atualizar Exercicio";

};

async function deletarExercicio(id) {

    if (!confirm("Deseja realmente Deletar esse Exercicio??")) return

    await fetch(`${API}/${id}`, { method: "DELETE" });

    alert("Exercicio Deletado!!");
    listarTodos();
};

listarTodos();