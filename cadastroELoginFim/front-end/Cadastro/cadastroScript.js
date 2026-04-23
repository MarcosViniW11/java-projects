const API="http://localhost:8080/auth/register";


document.getElementById("emailId").addEventListener("input",verificar)
document.getElementById("senhaId").addEventListener("input",verificar)

document.getElementById("botaoCadastrar").disabled=true;

document.getElementById("form-cadastro").addEventListener("submit", async (pagina) => {
    pagina.preventDefault();

    const cadastro={
        email:document.getElementById("emailId").value,
        senha:document.getElementById("senhaId").value
    };

    if(cadastro.email==="" || cadastro.senha===""){alert("Valores em branco"); return;}

    const res = await fetch(API,{
        method:"POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify(cadastro)
    });

    if(!res.ok){alert("Ocorreu um erro ao Cadastrar o usuario, email/senha invalidos ou ja existente!");return;}

    alert("Cadastrado com sucesso, redimencionando para pagina de login");
    window.location.href="../Login/login.html";
})


function verificar(){
    email=document.getElementById("emailId").value
    senha=document.getElementById("senhaId").value

    botao=document.getElementById("botaoCadastrar")

    botao.disabled = email==="" || senha==="";

}

