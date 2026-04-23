const API="http://localhost:8080/auth/login"

document.getElementById("botaoLogin").disabled=true;

document.getElementById("emailId").addEventListener("input",verificar);
document.getElementById("senhaId").addEventListener("input",verificar)

function parseJwt(token) {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    return JSON.parse(atob(base64));
}


document.getElementById("form-login").addEventListener("submit", async(pagina) => {
    pagina.preventDefault();

    const login={
        email:document.getElementById("emailId").value,
        senha:document.getElementById("senhaId").value
    }

    if(login.email==="" || login.senha===""){alert("Não é possivel fazer login com campo(s) em branco"); return;}

    const res=await fetch(API,{
        method:"POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify(login)
    });

    if(!res.ok){alert("Ocorreu um erro ao logar, senha ou email invalido(s)"); return;}

    const data= await res.json();
    const token= data.token;

    localStorage.setItem("token", token);

    const payload=parseJwt(token);

    if(payload.role==="ADMIN"){
        window.location.href = "../admin/index.html";
    }else{
        window.location.href= "../usuario/index.html";
    }


})

function verificar(){
    inputEmail=document.getElementById("emailId").value;
    inputSenha=document.getElementById("senhaId").value;

    botao=document.getElementById("botaoLogin")

    botao.disabled= inputEmail==="" || inputSenha==="";

}

