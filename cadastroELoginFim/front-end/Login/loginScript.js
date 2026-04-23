const API = "http://localhost:8080/auth/login"

document.getElementById("botaoLogar").disabled=true;

document.getElementById("emailId").addEventListener("input",verificar);
document.getElementById("senhaId").addEventListener("input",verificar);

function parseJwt(token) {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, "+").replace(/-/g, "/");
    return JSON.parse(atob(base64));
}

document.getElementById("form-login").addEventListener("submit", async (pagina) => {
    pagina.preventDefault();

    const login = {
        email: document.getElementById("emailId").value,
        senha: document.getElementById("senhaId").value
    }

    if (login.email === "" || login.senha === "") { alert("Campos em Branco!!"); return; }

    const res = await fetch(API, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(login)
    });

    if (!res.ok) { alert("Ocorreu um erro ao gerar o token, senha/email invalido(s)"); return; }

    const data = await res.json()
    const token = await data.token;

    localStorage.setItem("token", token);

    const payload = parseJwt(token);

    if (payload.role === "ADMIN") {
        alert("Logado como Admin, redimencionando Para a pagina de admin...");
        window.location.href = "../Admin/admin.html";
    } else {
        alert("Logado com usuario, redimencionando Para a pagina de usuario...");
        window.location.href = "../User/user.html";
    }


});

function verificar(){
    email=document.getElementById("emailId").value
    senha=document.getElementById("senhaId").value

    botao=document.getElementById("botaoLogar")

    botao.disabled = email==="" || senha==="";

}
