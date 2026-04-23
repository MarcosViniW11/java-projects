let listaExerciciosTemporaria = [];
let currentUserId = null; // Guardamos o ID do usuário atual aqui

async function carregarPerfil() {
    const email = document.getElementById('searchEmail').value;
    const card = document.getElementById('perfilUsuario');
    const formTreino = document.getElementById('secaoNovoTreino');
    
    if (!email) return;

    try {
        const response = await fetch(`http://localhost:8080/usuarios/buscar/${email}`);
        
        if (!response.ok) {
            alert("Usuário não encontrado!");
            card.style.display = 'none';
            return;
        }

        const user = await response.json();
        currentUserId = user.id; // Atribui o ID para uso no cadastro de treino

        // Preenche dados básicos
        document.getElementById('userEmailDisplay').innerText = `👤 ${user.email}`;
        document.getElementById('userId').innerText = user.id;
        document.getElementById('userPeso').innerText = user.avaliacao ? user.avaliacao.peso : "---";
        document.getElementById('userAltura').innerText = user.avaliacao ? user.avaliacao.altura : "---";

        // Preenche Treinos
        renderizarTreinos(user.treinos);

        card.style.display = 'block';
        formTreino.style.display = 'none'; // Garante que o form comece fechado

    } catch (e) {
        console.error(e);
        alert("Erro ao buscar dados.");
    }
}

function renderizarTreinos(treinos) {
    const listaTreinos = document.getElementById('listaTreinos');
    listaTreinos.innerHTML = '';

    if (treinos && treinos.length > 0) {
        treinos.forEach(treino => {
            const treinoDiv = document.createElement('div');
            treinoDiv.className = 'workout-card';
            
            let exerciciosHtml = '';
            treino.exerciciosRelacionados.forEach(item => {
                exerciciosHtml += `
                    <div class="ex-list">
                        💪 <strong>${item.exercicio.nome}</strong>: 
                        ${item.series}x${item.repeticoes} - ${item.carga} 
                        <small>(${item.exercicio.descricao})</small>
                    </div>`;
            });

            treinoDiv.innerHTML = `<strong>🏋️ ${treino.nome}</strong><br>${exerciciosHtml}`;
            listaTreinos.appendChild(treinoDiv);
        });
    } else {
        listaTreinos.innerHTML = '<p>Nenhum treino montado ainda.</p>';
    }
}

// --- FUNÇÕES PARA O NOVO TREINO ---

function toggleFormTreino() {
    const form = document.getElementById('secaoNovoTreino');
    form.style.display = form.style.display === 'block' ? 'none' : 'block';
    listaExerciciosTemporaria = []; // Limpa a lista ao abrir/fechar
    document.getElementById('listaExsTemp').innerHTML = '';
}

function adicionarExercicioALista() {
    const id = document.getElementById('tempExId').value;
    const series = document.getElementById('tempSeries').value;
    const reps = document.getElementById('tempReps').value;
    const carga = document.getElementById('tempCarga').value;

    if (!id || !series || !reps) {
        alert("Preencha ID, Séries e Repetições!");
        return;
    }

    const item = {
        series: parseInt(series),
        repeticoes: parseInt(reps),
        carga: carga,
        exercicio: { id: parseInt(id) }
    };

    listaExerciciosTemporaria.push(item);
    
    // Mostra na pequena lista visual
    const listaDiv = document.getElementById('listaExsTemp');
    listaDiv.innerHTML += `<div>• Ex ID: ${id} (${series}x${reps})</div>`;

    // Limpa campos temporários
    document.getElementById('tempExId').value = '';
    document.getElementById('tempSeries').value = '';
    document.getElementById('tempReps').value = '';
    document.getElementById('tempCarga').value = '';
}

async function salvarTreino() {
    const nome = document.getElementById('novoNomeTreino').value;

    if (!nome || listaExerciciosTemporaria.length === 0) {
        alert("Dê um nome ao treino e adicione pelo menos um exercício!");
        return;
    }

    const payload = {
        nome: nome,
        usuario: { id: currentUserId },
        exerciciosRelacionados: listaExerciciosTemporaria
    };

    try {
        const response = await fetch('http://localhost:8080/treinos', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        if (response.ok) {
            alert("Treino adicionado!");
            // Recarrega o perfil para mostrar o treino novo na lista
            carregarPerfil(); 
        } else {
            alert("Erro ao salvar treino.");
        }
    } catch (e) {
        alert("Falha na conexão com o servidor.");
    }
}