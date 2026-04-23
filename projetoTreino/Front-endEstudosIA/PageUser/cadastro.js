const API = "http://localhost:8080/usuarios/cadastrar"


async function cadastrar() {

    const cadastro = {
        email: document.getElementById("emailID").value,
        avaliacao: {
            peso: document.getElementById("pesoID").value,
            altura: document.getElementById("alturaID").value
        }
    }

    const res = await fetch(API, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(cadastro)
    })

    if (!res.ok) {
        const retorno = await res.json();
        const mensagem = retorno.message || retorno.mensage || "Erro desconhecido";
        alert("OCORREU UM ERRO: " + mensagem);
        return;
    }

    alert("USUARIO CADASTRADO COM SUCESSO, redimencionando para a pagina de informações...")
    window.location.href = "../PageUser/user.html";
}
