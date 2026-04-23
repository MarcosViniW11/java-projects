const API="http://localhost:8080/clientes";
let idEdicao=null;

document.getElementById("form-clientes").addEventListener("submit", async(pagina)=>{
    pagina.preventDefault();

    const cliente={
        nome:document.getElementById("nomeId").value,
        email:document.getElementById("emailId").value,
        telefone:document.getElementById("telefoneId").value,
    }

    if(idEdicao!==null){
        const res=await fetch(`${API}/${idEdicao}`,{
            method:"PUT",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(cliente)
        });

        if(!res.ok){
            alert("Ocorreu um erro ao atulizar o cliente!!");
            idEdicao=null;
            return;
        }

        alert("Cliente atulizado com sucesso!");
        idEdicao=null;
        document.getElementById("botaoCadastrarCliente").innerText="Cadastrar Cliente";
    }else{
        const res=await fetch(API,{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(cliente)
        });

        if(!res.ok){
            alert("Erro ao cadastrar Cliente!!");
            return;
        }

        alert("Cliente Cadastrado(a) Com sucesso!!")

    }

    document.getElementById("form-clientes").reset();
    listarTodos();

});

async function listarTodos() {
    const res=await fetch(API);
    const clientes=await res.json();

    let html="<table border=1><tr><th>ID</th><th>Nome:</th><th>Email:</th><th>Telefone</th><th>Ações:</th></tr>"

    clientes.forEach(cliente=>{
        html+=`
        <tr>
            <td>${cliente.id}</td>
            <td>${cliente.nome}</td>
            <td>${cliente.email}</td>
            <td>${cliente.telefone}</td>
            <td>
                <button type="button" id="botaoEditar" onclick="carregarCliente(${cliente.id})">Editar</button>
                <button type="button" id="botaoDeletar" onclick="deletarCliente(${cliente.id})">Deletar</button>
            </td>
        </tr>
        `
    });

    html+="</table>"
    document.getElementById("lista-clientes").innerHTML=html
}

listarTodos();

async function carregarCliente(id) {
    const res=await fetch(`${API}/${id}`);
    const cliente=await res.json();

    document.getElementById("nomeId").value=cliente.nome;
    document.getElementById("emailId").value=cliente.email;
    document.getElementById("telefoneId").value=cliente.telefone;

    idEdicao=id;

    document.getElementById("botaoCadastrarCliente").innerText="Atualizar Cliente";
    
};

async function deletarCliente(id) {
    if(!confirm("Deseja realmente deletar o Cliente selecionado??")){ return };

    const res=await fetch(`${API}/${id}`,{ method:"DELETE" });

    if(!res.ok){ alert("Ocorreu um erro ao deletar o cliente selecionado!"); return };

    alert("Cliente deletado Com sucesso!!");
    
    listarTodos();
    
}


