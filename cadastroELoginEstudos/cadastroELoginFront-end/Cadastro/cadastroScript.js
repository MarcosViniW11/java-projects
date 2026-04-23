const API="http://localhost:8080/auth/register";

document.getElementById("botaoCadastrar").disabled=true;

document.getElementById("emailId").addEventListener("input",verificarFormulario);
document.getElementById("senhaId").addEventListener("input",verificarFormulario);

document.getElementById("form-cadastro").addEventListener("submit",async (pagina) => {
    pagina.preventDefault();

    const cadastro={
        email:document.getElementById("emailId").value,
        senha:document.getElementById("senhaId").value
    }

    if(cadastro.email==="" || cadastro.senha===""){
        alert("Não se pode cadastrar um usuario com campo(s) em branco(s)");
        return;
    }

    const res=await fetch(API,{
        method:"POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify(cadastro)
    });

    if(!res.ok || res.status!==200){
        alert("Ocorreu um erro ao cadastrar o usuario / email ou senha invalidos");
        return
    }

    alert("Usuario Cadastrado Com sucesso!!");
    alert("Redimencionando Para Pagina de Login!!")
    window.location.href="../Login/login.html";
});

function verificarFormulario(){
    emailInput=document.getElementById("emailId").value
    senhaInput=document.getElementById("senhaId").value

    botao=document.getElementById("botaoCadastrar");

    botao.disabled = emailInput==="" || senhaInput==="";

}
