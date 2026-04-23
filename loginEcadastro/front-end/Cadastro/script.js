const API="http://localhost:8080/auth/register"

document.getElementById("botaoCadastrar").disabled=true;

document.getElementById("emailId").addEventListener("input",verificar)
document.getElementById("senhaId").addEventListener("input",verificar)

document.getElementById("form-cadastro").addEventListener("submit", async(pagina) => {
    pagina.preventDefault();

    const registro={
        email:document.getElementById("emailId").value,
        senha:document.getElementById("senhaId").value
    }

    if(registro.email==="" || registro.senha===""){alert("Não pode fazer um registro com campos em branco"); return;}

    const res= await fetch(API,{
        method:"POST",
        headers:{"Content-type":"application/json"},
        body:JSON.stringify(registro)
    });

    if(!res.ok){alert("Ocorreu um erro ao fazer o registro!!"); return;}

    window.location.href="../Login/index.html"


});

function verificar(){
    inputemail=document.getElementById("emailId").value
    inputsenha=document.getElementById("senhaId").value

    botao=document.getElementById("botaoCadastrar")

    botao.disabled= inputemail==="" || inputsenha==="";

}

