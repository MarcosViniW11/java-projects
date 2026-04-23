const API_URL = "http://localhost:8080/exercicios";

// 1. CADASTRAR NOVO EXERCÍCIO
async function cadastrarExercicio() {
    const nome = document.getElementById('nomeEx').value;
    const descricao = document.getElementById('descEx').value;

    if (!nome || !descricao) {
        alert("Preencha todos os campos!");
        return;
    }

    const payload = {
        nome: nome,
        descricao: descricao
    };

    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        if (response.ok) {
            alert("Exercício cadastrado com sucesso!");
            document.getElementById('nomeEx').value = '';
            document.getElementById('descEx').value = '';
            listarTodos(); // Atualiza a tabela automaticamente
        } else {
            alert("Erro ao cadastrar exercício.");
        }
    } catch (error) {
        console.error("Erro:", error);
    }
}

// 2. BUSCAR POR ID OU LISTAR TUDO
async function buscarOuListar() {
    const id = document.getElementById('searchId').value;
    
    if (id) {
        // Busca individual
        buscarPorId(id);
    } else {
        // Listagem geral
        listarTodos();
    }
}

async function buscarPorId(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        if (!response.ok) throw new Error("Não encontrado");
        
        const exercicio = await response.json();
        renderizarTabela([exercicio]); // Passa como array para reusar a função
    } catch (error) {
        alert("Exercício com ID " + id + " não encontrado.");
        listarTodos();
    }
}

async function listarTodos() {
    document.getElementById('searchId').value = ''; // Limpa o campo de busca
    try {
        const response = await fetch(API_URL);
        const lista = await response.json();
        renderizarTabela(lista);
    } catch (error) {
        console.error("Erro ao listar:", error);
    }
}

// 3. AUXILIAR: RENDERIZA OS DADOS NA TABELA
function renderizarTabela(dados) {
    const tbody = document.getElementById('corpoTabela');
    tbody.innerHTML = '';

    dados.forEach(ex => {
        const row = `
            <tr>
                <td><span class="id-badge">${ex.id}</span></td>
                <td><strong>${ex.nome}</strong></td>
                <td>${ex.descricao}</td>
            </tr>
        `;
        tbody.innerHTML += row;
    });
}

// Carregar a lista assim que abrir a página
window.onload = listarTodos;