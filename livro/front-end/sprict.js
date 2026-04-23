const API = "http://localhost:8080/livros";

// Atacha o evento no formulário assim que a página carregar
document.addEventListener("DOMContentLoaded", () => {
    document.querySelector("form").addEventListener("submit", cadastrar);
});

function cadastrar(event) {
    event.preventDefault(); // evita reload

    const livro = {
        titulo: document.getElementById("titulo").value,
        autor: document.getElementById("autor").value,
        dataLancamento: document.getElementById("dataLancamento").value
    };
    listar();
    fetch(API, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(livro)
    })
    .then(response => {
        if (!response.ok) throw new Error("Erro ao cadastrar");
        return response.json();
    })
    .then(data => {
        alert("Livro cadastrado com sucesso!");
        console.log(data);
        
    })
    .catch(error => {
        console.error(error);
        alert("Falha ao cadastrar o livro.");
    });
    
}

async function listar(){
    const res= await fetch(API);
    const lista= await res.json();

    //let html=("<p>id:        ;titulo:           ;autor:            ;dataLançamento:          </p>")

    let html="<table><tr><th>ID</th><th>Titulo</th><th></th><th>Autor</th><th>Data de lançamento</th><th>Ações</th></tr>";

    lista.forEach(livro => {
        //html+=`<p>${livro.id},${livro.titulo},${livro.autor},${livro.dataLancamento}</p>`

        html += `
        <tr>
            <td>${livro.id}</td>
            <td>${livro.titulo}</td>
            <td>${livro.autor}</td>
            <td>${livro.dataLancamento}</td>
            <td>
                <button class="editar" onclick="carregarProduto(${livro.id})">Editar</button>
                <button class="excluir" onclick="deletarProduto(${livro.id})">Excluir</button>
            </td>
        </tr>`;

    });

    html+="</table>"

    document.getElementById("Lista").innerHTML=html;

}


