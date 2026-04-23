const API_URL = "http://localhost:8080/usuarios";

async function verificarEmail() {
    const email = document.getElementById('email').value;
    const msg = document.getElementById('msg');
    
    if (!email) {
        alert("Digite um e-mail para verificar");
        return;
    }

    try {
        const response = await fetch(`${API_URL}/buscar/${email}`);
        if (response.ok) {
            msg.innerText = "❌ E-mail já cadastrado!";
            msg.className = "message error";
        } else {
            msg.innerText = "✅ E-mail disponível!";
            msg.className = "message success";
        }
    } catch (e) {
        console.error(e);
    }
}

async function cadastrarUsuario() {
    const email = document.getElementById('email').value;
    const peso = document.getElementById('peso').value;
    const altura = document.getElementById('altura').value;

    const payload = {
        email: email,
        avaliacao: {
            peso: parseFloat(peso),
            altura: parseFloat(altura)
        }
    };

    try {
        const response = await fetch(`${API_URL}/cadastrar`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        if (response.ok) {
            alert("Usuário e Avaliação cadastrados com sucesso!");
            window.location.href = "../perfil/perfil.html"; // Redireciona para o perfil
        } else {
            alert("Erro ao cadastrar. Verifique se o e-mail é único.");
        }
    } catch (e) {
        alert("Erro de conexão com o servidor.");
    }
}