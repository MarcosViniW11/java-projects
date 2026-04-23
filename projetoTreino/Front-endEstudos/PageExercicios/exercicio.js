const API = "http://localhost:8080/exercicios";


async function cadastrar() {
    exercicio={
        nome:document.getElementById("nomeID").value,
        descricao:document.getElementById("descID").value
    }

    const res = await fetch(API,{
        method:"POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify(exercicio)
    })

    if (!res.ok) {
        const retorno = await res.json();
        const mensagem = retorno.message || retorno.mensage || "Erro desconhecido";
        alert("OCORREU UM ERRO: " + mensagem);
        return;
    }

    alert("Exercicio Cadastrado com sucesso!!")
    document.getElementById("nomeID").value=""
    document.getElementById("descID").value=""
    mostrarExercicios();
}

async function mostrarExercicios() {
    const res = await fetch(API);
    exercicios = await res.json();


    let table = "<table border=1><tr><th>ID:</th><th>Nome:</th><th>Descrição:</th></tr>"
    exercicios.forEach(exerc => {
        table += `<tr>
            <td>${exerc.id}</td>
            <td>${exerc.nome}</td>
            <td>${exerc.descricao}</td>
        </tr>`
    });

    document.getElementById("lista").innerHTML=table;
}

mostrarExercicios()
