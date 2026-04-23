const API="http://localhost:8080/usuarios/buscar";


async function listarTreinos() {
    email = document.getElementById("emailUserId").value
    const res = await fetch(`${API}/${email}`);

    if (!res.ok) {
        const retorno = await res.json();
        const mensagem = retorno.message || retorno.mensage || "Erro desconhecido";
        alert("OCORREU UM ERRO: " + mensagem);
        return;
    }

    const UserInformations = await res.json();
    const treinos = UserInformations.treinos;

    if(treinos.length <= 0){
        alert("Usuario não possui nenhum treino Cadastrado ainda");
        document.getElementById("listaTreinos").innerHTML="<br><button type = 'button' onclick='cadastrarTreino()'>Cadastrar um Primeiro Treino</button>";
        return;
    }

    let table = `
        <h3>Lista De Treino: ${UserInformations.id}</h3>
        <p>Email: ${UserInformations.email}</p>
        
        <p><b>Avaliação:</b></p>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Peso</th>
                    <th>Altura</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${UserInformations.avaliacao.id}</td>
                    <td>${UserInformations.avaliacao.peso} kg</td>
                    <td>${UserInformations.avaliacao.altura} m</td>
                </tr>
            </tbody>
        </table>
        
        <br>
        <table border="1">
            <thead>
                <tr>
                    <th>Treino</th>
                    <th>ID Exercício</th>
                    <th>Nome Exercício</th>
                    <th>Descrição</th>
                    <th>Séries</th>
                    <th>Repetições</th>
                    <th>Carga</th>
                </tr>
            </thead>
            <tbody>`;

    treinos.forEach(treino => {
        treino.exerciciosRelacionados.forEach(item => {
            table += `
                <tr>
                    <td>${treino.nome}</td>
                    <td>${item.exercicio.id}</td>
                    <td>${item.exercicio.nome}</td>
                    <td>${item.exercicio.descricao}</td>
                    <td>${item.series}</td>
                    <td>${item.repeticoes}</td>
                    <td>${item.carga}</td>
                </tr>`;
        });
    });

    table += "</tbody></table>";
    table += "<br><button type = 'button' onclick='cadastrarTreino()' >Cadastrar Treino</button>"
    document.getElementById("listaTreinos").innerHTML = table;
}

async function cadastrarTreino() {
    alert("redimencionando para pagina de cadastro de treino!")
    window.location.href="../PageTreino/treino.html";
}

