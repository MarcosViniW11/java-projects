const API="http://localhost:8080/livros";
let idEdição = null;

document.getElementById("form-de-livro").addEventListener("submit", async (e) => {
    e.preventDefault();

    const livro={
        titulo: document.getElementById("titulo").value,
        autor: document.getElementById("autor").value,
        dataLancamento: Number(document.getElementById("dataLancamento").value)
    };


    if (idEdição !== null){
        await fetch(`${API}/${idEdição}`,{
            method:"PUT",
            headers:{ "Content-Type" : "application/json"},
            body: JSON.stringify(livro)
        });

        alert("Produto atualizado")

        idEdição=null;
        document.querySelector("input[type='submit']").innerHTML="Cadastrar";

    }else{

        await fetch(API, {
            method:"POST",
            headers:{"Content-type": "application/json"},
            body: JSON.stringify(livro)
        })

        alert("Livro Cadastrado")

    }

    document.getElementById("form-de-livro").reset();
    listarLivros();


})

async function listarLivros() {

    const res= await fetch(API);
    const lista= await res.json();

    let html="<table><tr><th>ID</th><th>Titulo</th><th>autor</th<th>data de lançamento</th><th>Ações</th></tr>";

    lista.forEach(l => {
        html+=`
        <tr>
            <td>${l.id}</td>
            <td>${l.titulo}</td>
            <td>${l.autor}</td>
            <td>${l.dataLancamento}</td>

            <td>

            <button class="editar" onclick="carregarProduto(${l.id})">Editar</button>
            <button class="excluir" onclick="deletarProduto(${l.id})">Excluir</button>
            
            </td>
        
        </tr>`
    });
    

    html+="</table>";

    document.getElementById("Lista").innerHTML= html;
}

async function carregarProduto(id) {

    const res= await fetch(`${API}/${id}`);
    const livro= await res.json();

    idEdição=id;

    document.getElementById("titulo").value=livro.titulo;
    document.getElementById("autor").value=livro.autor;
    document.getElementById("dataLancamento").value=livro.dataLancamento;

    document.querySelector("input[type='submit']").innerHTML="Atualizar";
    
}

async function deletarProduto(id) {
    if(!confirm("Deseja excluir esse livro?")) return;

    await fetch(`${API}/${id}` , { method: "DELETE" });

    alert("Livro Removido!!")
    listarLivros();
}

listarLivros();

