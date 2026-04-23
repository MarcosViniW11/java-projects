const API="http://localhost:8080/auth/cadastro";

document.getElementById("botaoCadastrar").disabled=true;

document.getElementById("emailId").addEventListener("input",verificarFormulario);
document.getElementById("senhaId").addEventListener("input",verificarFormulario);

document.getElementById("formCadastro").addEventListener("submit", async(pagina) => {
    pagina.preventDefault();

    const login={
        email:document.getElementById("emailId").value,
        senha:document.getElementById("senhaId").value
    };

    if(login.email === "" || login.senha ===""){
        alert("Campos em branco...");
        return;
    }

    const res = await fetch(API,{
        method:"POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify(login)
    });

    if(!res.ok){alert("Ocorreu um erro ao Cadastrar"); return;}

    alert("Cadastrado com sucesso, redimencionando para pagina de login");
    window.location.href="../Login/Login.html";

});

function verificarFormulario(){
    email=document.getElementById("emailId").value;
    senha=document.getElementById("senhaId").value;

    botao = document.getElementById("botaoCadastrar");
    botao.disabled = email==="" || senha===""
}

