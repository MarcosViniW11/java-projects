const API="http://localhost:8080/vendas";
let idEdicao=null;

document.getElementById("form-Venda").addEventListener("submit",async (pagina) => {
    pagina.preventDefault();

    const Venda={
        cliente:{id:Number(document.getElementById("idCliente").value)},
        produto:{id:Number(document.getElementById("idProduto").value)},
        valorTotal:Number(document.getElementById("valorId").value),
        quantidadeASerVendido:Number(document.getElementById("quantidadeVendidoId").value)
    }


    if(idEdicao!==null){
        await fetch(`${API}/${idEdicao}`,{
            method:"PUT",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(Venda)
        });

        alert("Venda Atualizada")

        idEdicao=null;

        document.querySelector("button[type='submit']").innerText="Cadastrar Venda"
        listarTodos();


    }else{
        await fetch(API,{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(Venda)
        });

        alert("Venda Cadastrada!!")

        document.getElementById("form-Venda").reset();
        listarTodos();

    }


});

async function listarTodos() {
    const res=await fetch(API);
    const Vendas=await res.json();

    html=`<table class="Vendas"><tr><th>Id:</th><th>ClienteId</th><th>ProdutoId</th><th>Valor Total:</th><th>qtbASerVendido:</th><th>Ações:</th></tr>`

    Vendas.forEach(venda=>{
        html+=`
        <tr>
            <td>${venda.id}</td>
            <td>${venda.cliente.id}</td>
            <td>${venda.produto.id}</td>
            <td>${venda.valorTotal}</td>
            <td>${venda.quantidadeASerVendido}</td>

            <td>
                <button class="botaoCarregar" onclick="carregarVenda(${venda.id})">Editar</button>
                <button class="botatoExcluir" onclick="deletarVenda(${venda.id})">Excluir</button>
            </td>

        </tr>
        `

    });

    html+="</table";

    document.getElementById("ListaVenda").innerHTML=html;
};

async function carregarVenda(id) {
    const res=await fetch(`${API}/${id}`);
    const venda=await res.json();

    document.getElementById("idCliente").value=venda.cliente.id;
    document.getElementById("idProduto").value=venda.produto.id;
    document.getElementById("valorId").value=venda.valorTotal;
    document.getElementById("quantidadeVendidoId").value=venda.quantidadeASerVendido;

    idEdicao=id;
    document.querySelector("button[type='submit']").innerText="Atualizar Venda"
    
};

async function deletarVenda(id) {

    if(!confirm("Deseja deletar essa venda??")) return;

    await fetch(`${API}/${id}`,{method:"DELETE"});

    alert("Venda Deletada Com sucesso!!");
    listarTodos();
};



listarTodos();

