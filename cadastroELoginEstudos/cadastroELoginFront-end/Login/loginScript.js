const API="http://localhost:8080/auth/login"


document.getElementById("botaoLogin").disabled=true;

document.getElementById("emailId").addEventListener("input",verificarForm)
document.getElementById("senhaId").addEventListener("input",verificarForm)


function parseJwt(token){
    const base64Url = token.split('.')[1];
    const base64= base64Url.replace(/-/g,"+").replace(/_/g,'/');
    return JSON.parse(atob(base64))
}

document.getElementById("form-login").addEventListener("submit", async (pagina) => {
    pagina.preventDefault();

    const login={
        email:document.getElementById("emailId").value,
        senha:document.getElementById("senhaId").value
    }

    if(login.email==="" || login.senha===""){
        alert("Não da para fazer login com campo(s) em branco(s)")
        return;
    }

    const res=await fetch(API,{
        method:"POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify(login)
    });

    if(!res.ok){alert("ocorreu um erro ao gerar token, email/senha invalidos"); return}

    const data=await res.json();
    const token=data.token;

    localStorage.setItem("token",token);

    const payload=parseJwt(token);

    if(payload.role==="ADMIN"){
        alert("Logado com admin, redimencionando Pagina...");
        window.location.href="../paginaAdmin/admin.html"
    }else{
        alert("Logado com usuario, redimencionando Pagina...");
        window.location.href="../paginaUsuario/usuario.html"
    }

})

function verificarForm(){
    email=document.getElementById("emailId").value
    senha=document.getElementById("senhaId").value

    botao=document.getElementById("botaoLogin");

    botao.disabled = email==="" || senha==="";
}

