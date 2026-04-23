const API = "http://localhost:8080/vendas"// parte de venda
let idEdicao = null;

let listaItensVenda = [];

let indexItemEdicao = null;

document.getElementById("botaoCadastrarVendas").disabled = true;

document.getElementById("clienteId").addEventListener("input", verificarFormulario);

function carregarItemVendaNaLista() {

    const item_venda = {
        produto: { id: Number(document.getElementById("produtoId").value) },
        quantidadeVendida: Number(document.getElementById("qtbVendidaId").value)
    };

    if (indexItemEdicao !== null) {
        listaItensVenda[indexItemEdicao] = item_venda;
        document.getElementById("botaoCadastrarItensVenda").innerText = "Cadastrar Item Venda"
    } else {
        listaItensVenda.push(item_venda);
    }


    document.getElementById("produtoId").value = "";
    document.getElementById("qtbVendidaId").value = "";

    indexItemEdicao = null;

    mostrarItens();
    verificarFormulario();

}

function mostrarItens() {
    let lista = "<table border=1><tr><th>Id do produto</th><th>Quantidade Vendida</th><th>Ações</th></tr>"

    listaItensVenda.forEach(item => {
        lista += `
        <tr>
            <td>${item.produto.id}</td>
            <td>${item.quantidadeVendida}</td>
            <td>
                <button type="button" onclick="editarItemVenda(${item.produto.id})">Editar</button>
                <button type="button" onclick="deletarItemVenda(${item.produto.id})">Deletar</button>
            </td>
        </tr>
        `
    });

    lista += "</table>"
    document.getElementById("ListaItens-Venda").innerHTML = lista;

}

function deletarItemVenda(idProduto) {
    listaItensVenda = listaItensVenda.filter(item => item.produto.id != idProduto);
    mostrarItens();
    verificarFormulario();
}

function editarItemVenda(idProduto) {
    const index = listaItensVenda.findIndex(item => item.produto.id === idProduto);// pegamos o indice do item...

    if (index === -1) return;

    const item = listaItensVenda[index];

    document.getElementById("produtoId").value = item.produto.id;
    document.getElementById("qtbVendidaId").value = item.quantidadeVendida;

    document.getElementById("botaoCadastrarItensVenda").innerText = "Atualizar Item Venda"

    indexItemEdicao = index
};

function verificarFormulario() {
    const campoClienteId = document.getElementById("clienteId").value;
    const botaoItemVenda = document.getElementById("botaoCadastrarVendas");

    botaoItemVenda.disabled = campoClienteId === "" || listaItensVenda.length === 0

}

/// parte de Vendas

document.getElementById("form-vendas").addEventListener("submit", async (pagina) => {
    pagina.preventDefault();

    const venda = {
        cliente: { id: Number(document.getElementById("clienteId").value) },
        dataVenda: document.getElementById("dataId").value,
        listaItens: listaItensVenda
    };

    if (idEdicao != null) {
        const res = await fetch(`${API}/${idEdicao}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(venda)
        });

        if (!res.ok) {
            alert("Ocorreu um erro ao Editar o Venda");
            return;
        }
        document.getElementById("botaoCadastrarVendas").innerText = "Cadastrar Venda"
        alert("Venda Atulizado com sucesso!!")

    } else {
        const res = await fetch(API, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(venda)
        });

        if (!res.ok) {
            alert("Ocorreu um erro ao Cadastrar a Venda");
            return;
        }

        alert("Venda Cadastrada Com sucesso!!")

    }

    document.getElementById("form-vendas").reset();

    listaItensVenda = [];

    mostrarItens();



});

async function buscarVendaPorId() {
    let id = Number(document.getElementById("vendaBuscarId").value)

    const res = await fetch(`${API}/${id}`);

    if (!res.ok) { alert("Venda Não Encontrada"); return };

    const venda = await res.json();

    let table = `
    <h3>Venda #${venda.id}</h3>
    <p>Cliente: ${venda.cliente.nome}</p>
    <p>Data Da Venda: ${venda.dataVenda}</p>
    <p>Total: ${venda.total}</p>

    <button type="button" onclick="editarVenda(${venda.id})">Editar</button>
    <button type="button" onclick="deletarVenda(${venda.id})">Deletar</button>
    
    `;

    listaItens = venda.listaItens;

    table += `<table border=1><tr><th>ID Do produto:</th><th>Nome:</th><th>QTB Estoque:</th><th>Valor Unitario:</th><th>QTB Vendida:</th><th>Subtotal</th></tr>`


    listaItens.forEach(item => {
        table += `
        <tr>
            <td>${item.produto.id}</td>
            <td>${item.produto.nome}</td>
            <td>${item.produto.quantidadeEstoque}</td>
            <td>${item.produto.valorUnitario}</td>
            <td>${item.quantidadeVendida}</td>
            <td>${item.subTotal}</td>
        </tr>
        `
    });

    table += "</table>"
    document.getElementById("lista Vendas").innerHTML = table;


};

async function editarVenda(id) {
    const res = await fetch(`${API}/${id}`);

    if (!res.ok) { alert("não for possivel buscar A Venda Para Edita-la"); return; };

    const venda = await res.json();

    document.getElementById("clienteId").value = venda.cliente.id;
    document.getElementById("dataId").value = venda.dataVenda;

    listaItensVenda = [];

    venda.listaItens.forEach(item => {
        listaItensVenda.push(item);
    });

    mostrarItens();
    document.getElementById("botaoCadastrarVendas").innerText = "Atualizar Venda"
    document.getElementById("lista Vendas").innerHTML = "";
    idEdicao = id;


};

async function deletarVenda(id) {

    if (!confirm("Deseja Realmente deletar A venda selecionada??")) return;

    const res = await fetch(`${API}/${id}`, { method: "DELETE" });

    if (!res.ok) { alert("Ocorreu um erro ao deletar a Venda Selecionada!!"); return; }

    alert("Venda Deletada Com sucesso!!");
    document.getElementById("lista Vendas").innerHTML = "";

}




