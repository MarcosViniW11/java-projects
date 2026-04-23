const API="http://localhost:8080/servicos"
let idEdicao=null;

document.getElementById("botaoCadastrar").disabled=true;

document.getElementById("nomeServicoId").addEventListener("input",verificarFormulario);
document.getElementById("duraçãoMinutosId").addEventListener("input",verificarFormulario);
document.getElementById("preçoId").addEventListener("input",verificarFormulario);

function authFetch(url, options = {}) {
    const token = localStorage.getItem("token");

    if (!token) {
        alert("Usuário não autenticado");
        window.location.href = "../Login/index.html";
        return;
    }

    return fetch(url, {
        ...options,
        headers: {
            ...(options.headers || {}),
            "Authorization": `Bearer ${token}`,
            "Content-Type": "application/json"
        }
    });
}

document.getElementById("form-cadastro").addEventListener("submit", async(pagina)=>{
    pagina.preventDefault();

    const servico={
        nome:document.getElementById("nomeServicoId").value,
        duracaoMinutos:Number(document.getElementById("duraçãoMinutosId").value),
        preco:Number(document.getElementById("preçoId").value)
    };

    if(idEdicao!==null){
        const res=await authFetch(`${API}/${idEdicao}`,{
            method:"PUT",
            body:JSON.stringify(servico)
        });

        if(!res.ok){alert("Ocorreu um erro ao atualizar o Serviço selecionado!!"); return;};

        alert("Serviço atualizado Com sucesso!!");
        idEdicao=null;
        document.getElementById("botaoCadastrar").innerText="Cadastrar Serviço"
    }else{
        const res=await authFetch(API,{
            method:"POST",
            body:JSON.stringify(servico)
        });

        if(!res.ok){alert("houve um erro ao cadastrar o Serviço"); return;}

        alert("Serviço Cadastrado Com sucesso!!");
    }

    document.getElementById("form-cadastro").reset();
    listarTodos();
    verificarFormulario();
});

async function listarTodos() {
    const res=await authFetch(API);

    if(!res.ok){alert("Houve um erro ao listar!!"); return;}

    const lista=await res.json();

    let table="<table border=1><tr><th>ID:</th><th>Nome</th><th>Duração:(minutos)</th><th>Preço:</th><th>Ações</th></tr>"

    lista.forEach(servico=>{
        table+=`
        <tr>
            <td>${servico.id}</td>
            <td>${servico.nome}</td>
            <td>${servico.duracaoMinutos}</td>
            <td>${servico.preco}</td>
            <td>
                <button type="button" onclick="EditarServiço(${servico.id})">Editar</button>
                <button type="button" onclick="DeletarServiço(${servico.id})">Excluir</button>
            </td>
        </tr>
        `
    });

    table+="</table>";
    document.getElementById("ListaServiços").innerHTML=table;
    
};

async function EditarServiço(id) {
    const res=await authFetch(`${API}/${id}`);

    if(!res.ok){alert("Serviço não encontrado!!"); return;}

    const servico=await res.json();

    document.getElementById("nomeServicoId").value=servico.nome;
    document.getElementById("duraçãoMinutosId").value=servico.duracaoMinutos;
    document.getElementById("preçoId").value=servico.preco;

    document.getElementById("botaoCadastrar").innerText="Atualizar Serviço";
    idEdicao=id;
    
}

async function DeletarServiço(id) {
    if(!confirm("Deseja realmente deletar o serviço selecionado??")) return;

    const res=await authFetch(`${API}/${id}`,{ method:"DELETE" });

    if(!res.ok){alert("Não foi possivel deletar o serviço selecionado!!"); return;}

    alert("Serviço deletado Com sucesso!!");
    listarTodos();
    
}

function verificarFormulario(){
    const campoNomeServico=document.getElementById("nomeServicoId").value;
    const campoDuracaoServico=document.getElementById("duraçãoMinutosId").value
    const campoPrecoServico=document.getElementById("preçoId").value
    const botao=document.getElementById("botaoCadastrar");

    botao.disabled= campoNomeServico === "" || campoDuracaoServico === "" || campoPrecoServico === ""

}

listarTodos();

