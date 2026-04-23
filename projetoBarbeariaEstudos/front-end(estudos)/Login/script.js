const API_LOGIN = "http://localhost:8080/auth/login";

document.getElementById("form-login").addEventListener("submit", async (e) => {
    e.preventDefault();

    const email = document.getElementById("emailId").value;
    const senha = document.getElementById("senhaId").value;

    if (!email || !senha) {
        alert("Preencha email e senha");
        return;
    }

    const res = await fetch(API_LOGIN, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, senha })
    });

    if (!res.ok) {
        alert("Email ou senha inválidos");
        return;
    }

    const token = await res.text();

    // Guarda o token
    localStorage.setItem("token", token);

    // Redireciona para o sistema
    window.location.href = "../MenuPrincipal/index.html";
});
