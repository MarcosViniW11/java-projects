// ==============================
// CONFIGURAÇÃO
// ==============================
const API_URL = "http://localhost:8080/vendas"; // ajuste se necessário

// ==============================
// ELEMENTOS DO FORM
// ==============================
const clienteIdInput = document.getElementById("clienteId");
const dataVendaInput = document.getElementById("dataVenda");
const totalInput = document.getElementById("total");
const statusInput = document.getElementById("status");

const produtoIdInput = document.getElementById("produtoId");
const quantidadeInput = document.getElementById("quantidade");
const precoUnitarioInput = document.getElementById("precoUnitario");

const listaItensUl = document.getElementById("listaItens");

// Lista temporária de itens
let itens = [];

// ==============================
// ADICIONAR ITEM
// ==============================
function adicionarItem() {
    const produtoId = produtoIdInput.value;
    const quantidade = Number(quantidadeInput.value);
    const precoUnitario = Number(precoUnitarioInput.value);

    if (!produtoId || !quantidade || !precoUnitario) {
        alert("Preencha todos os campos do item!");
        return;
    }

    const subtotal = quantidade * precoUnitario;

    const item = {
        produto: { id: Number(produtoId) },
        quantidade,
        precoUnitario,
        subtotal
    };

    itens.push(item);
    atualizarListaItens();

    // limpar campos
    produtoIdInput.value = "";
    quantidadeInput.value = "";
    precoUnitarioInput.value = "";
}

// ==============================
// ATUALIZAR LISTA DE ITENS NO HTML
// ==============================
function atualizarListaItens() {
    listaItensUl.innerHTML = "";

    itens.forEach((item, index) => {
        const li = document.createElement("li");
        li.innerHTML = `
            Produto ID: ${item.produto.id} |
            Quantidade: ${item.quantidade} |
            Preço: R$ ${item.precoUnitario.toFixed(2)} |
            Subtotal: R$ ${item.subtotal.toFixed(2)}
        `;

        const btn = document.createElement("button");
        btn.textContent = "Remover";
        btn.onclick = () => {
            itens.splice(index, 1);
            atualizarListaItens();
        };

        li.appendChild(btn);
        listaItensUl.appendChild(li);
    });
}

// ==============================
// ENVIAR VENDA PARA O BACKEND
// ==============================
async function enviarVenda() {
    if (itens.length === 0) {
        alert("Adicione pelo menos um item!");
        return;
    }

    const venda = {
        cliente: { id: Number(clienteIdInput.value) },
        dataVenda: dataVendaInput.value,
        total: Number(totalInput.value),
        status: statusInput.value,
        itens: itens
    };

    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(venda)
        });

        if (!response.ok) {
            throw new Error("Erro ao cadastrar a venda.");
        }

        alert("Venda cadastrada com sucesso!");
        itens = []; 
        atualizarListaItens();

        document.getElementById("vendaForm").reset();

    } catch (error) {
        console.error(error);
        alert("Erro: " + error.message);
    }
}

function cadastrarCliente() {
    const cliente = {
        nome: document.getElementById("clienteNome").value,
        email: document.getElementById("clienteEmail").value
    };

    fetch("http://localhost:8080/clientes", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(cliente)
    })
    .then(r => r.json())
    .then(data => {
        alert("Cliente cadastrado com sucesso!");
        document.getElementById("clienteForm").reset();
    })
    .catch(err => console.error("Erro:", err));
}

function cadastrarProduto() {
    const produto = {
        nome: document.getElementById("produtoNome").value,
        preco: parseFloat(document.getElementById("produtoPreco").value)
    };

    fetch("http://localhost:8080/produtos", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(produto)
    })
    .then(r => r.json())
    .then(data => {
        alert("Produto cadastrado!");
        document.getElementById("produtoForm").reset();
    })
    .catch(err => console.error("Erro:", err));
}
