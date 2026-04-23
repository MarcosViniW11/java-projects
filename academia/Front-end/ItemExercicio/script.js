const API="http://localhost:8080/itens-exercicios";
let idEdicao=null;

document.getElementById("form-itemExercicio").addEventListener("submit", async(pagina) => {
    pagina.preventDefault();

    const itemExercicio={
        exercicio:{id:Number(document.getElementById("exercicioId").value)},
        series:Number(document.getElementById("qtbSeriesId").value),
        repeticoes:Number(document.getElementById("qtbRepeticoesId").value),
        tempoDescanco:Number(document.getElementById("tempoDescancoId").value)
    }

    if(idEdicao!==null){
        await fetch(`${API}/${idEdicao}`,{
            method:"PUT",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(itemExercicio)
        });

        alert("Item Exercicio atualizado com sucesso!!")
        idEdicao=null;
        document.querySelector("button[type='submit']").innerText="Cadastrar Item Exercicio";
    }else{
        await fetch(API,{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(itemExercicio)
        });

        alert("item Exercicio Cadastrado Com sucesso!!")

    }

    listarTodos();
    document.getElementById("form-itemExercicio").reset();


});

async function listarTodos() {
    const res=await fetch(API);
    const lista=await res.json();

    let html="<table><tr><th>ID:</th><th>Id do Exercicio</th><th>Series:</th><th>Repetições</th><th>Ações:</th></tr>"

    lista.forEach(itemExercicio=>{
        html+=`
        <tr>
            <td>${itemExercicio.id}</td>
            <td>${itemExercicio.exercicio.id}</td>
            <td>${itemExercicio.series}</td>
            <td>${itemExercicio.repeticoes}</td>
            <td>${itemExercicio.tempoDescanco}</td>
            <td>
                <button class="botaoEditar" onclick="carregarItemExercicio(${itemExercicio.id})">Editar</button>
                <button class="botaoDeletar" onclick="deletarItemExercicio(${itemExercicio.id})">Deletar</button>
            </td>
        </tr>
        `
    });

    html+="</table>";

    document.getElementById("listaItensExercicios").innerHTML=html;
    
};

async function carregarItemExercicio(id) {
    const res=await fetch(`${API}/${id}`);
    const itemExercicio=await res.json();

    document.getElementById("exercicioId").value=itemExercicio.exercicio.id;
    document.getElementById("qtbSeriesId").value=itemExercicio.series;
    document.getElementById("qtbRepeticoesId").value=itemExercicio.repeticoes;
    document.getElementById("tempoDescancoId").value=itemExercicio.tempoDescanco;

    idEdicao=id;
    document.querySelector("button[type='submit']").innerText="Atualizar item exercicio"
    
}

async function deletarItemExercicio(id) {
    if (!confirm("deseja Deletar esse item Exercicio??")) return;
    
    await fetch(`${API}/${id}`,{ method:"DELETE"} );

    alert("Iten exercicio Deletado Com sucesso!!")
    listarTodos();

}


listarTodos();

