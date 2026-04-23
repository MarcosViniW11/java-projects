// Array temporário para guardar os exercícios antes de enviar o treino
let listaExerciciosTemporaria = [];

// 1. Adiciona o exercício na lista visual e no array
function adicionarExercicioALista() {
    const id = document.getElementById('exId').value;
    const series = document.getElementById('exSeries').value;
    const repeticoes = document.getElementById('exReps').value;
    const carga = document.getElementById('exCarga').value;

    if (!id || !series || !repeticoes) {
        alert("Preencha os dados do exercício!");
        return;
    }

    const novoExercicio = {
        series: parseInt(series),
        repeticoes: parseInt(repeticoes),
        carga: carga,
        exercicio: { id: parseInt(id) }
    };

    listaExerciciosTemporaria.push(novoExercicio);
    renderizarListaTemporaria();
    
    // Limpa campos de exercício
    document.getElementById('exId').value = '';
    document.getElementById('exSeries').value = '';
    document.getElementById('exReps').value = '';
    document.getElementById('exCarga').value = '';
}

function renderizarListaTemporaria() {
    const div = document.getElementById('listaTemporaria');
    div.innerHTML = "<strong>Exercícios Pendentes:</strong>";
    listaExerciciosTemporaria.forEach((ex, index) => {
        div.innerHTML += `<div class="exercicio-item">#${ex.exercicio.id} - ${ex.series}x${ex.repeticoes} (${ex.carga})</div>`;
    });
}

// 2. Envia o JSON completo para o Back-end
async function salvarTreinoCompleto() {
    const userId = document.getElementById('userId').value;
    const nome = document.getElementById('nomeTreino').value;

    if (!userId || !nome || listaExerciciosTemporaria.length === 0) {
        alert("Preencha o usuário, nome do treino e adicione ao menos um exercício!");
        return;
    }

    const payload = {
        nome: nome,
        usuario: { id: parseInt(userId) },
        exerciciosRelacionados: listaExerciciosTemporaria
    };

    try {
        const response = await fetch('http://localhost:8080/treinos', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        if (response.ok) {
            alert("Treino salvo com sucesso!");
            listaExerciciosTemporaria = [];
            document.getElementById('nomeTreino').value = '';
            renderizarListaTemporaria();
        } else {
            alert("Erro ao salvar treino.");
        }
    } catch (error) {
        console.error("Erro:", error);
    }
}

// 3. Busca treinos do usuário e preenche a tabela
async function buscarTreinos() {
    const userId = document.getElementById('searchUserId').value;
    if (!userId) return;

    try {
        const response = await fetch(`http://localhost:8080/treinos/${userId}`);
        const treinos = await response.json();

        const tbody = document.querySelector('#tabelaTreinos tbody');
        tbody.innerHTML = '';

        treinos.forEach(t => {
            // Cria uma string com os exercícios formatados
            const listaExs = t.exerciciosRelacionados.map(item => 
                `• <strong>${item.exercicio.nome}</strong> (${item.exercicio.descricao}): 
                 ${item.series}x${item.repeticoes} - ${item.carga}`
            ).join('<br>');

            const row = `
                <tr>
                    <td>${t.id}</td>
                    <td>${t.nome}</td>
                    <td>${listaExs}</td>
                </tr>
            `;
            tbody.innerHTML += row;
        });
    } catch (error) {
        console.error("Erro ao buscar:", error);
    }
}