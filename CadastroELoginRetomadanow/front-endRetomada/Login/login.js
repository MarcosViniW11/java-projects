const API = "http://localhost:8080/auth/login"


function parseJwt(token){
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g,"+").replace(/-/g,"/");
    return JSON.parse(atob(base64));
}

document.getElementById("formLogin").addEventListener("submit", async(pagina) =>{
    pagina.preventDefault();

    const login = {
        email : document.getElementById("emailId").value,
        senha: document.getElementById("senhaId").value
    }

    if(login.email ==="" || login.senha===""){alert("Campos em branco"); return;}

    const res = await fetch(API,{
        method:"POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify(login)
    });

    if(!res.ok){alert("Ocorreu um erro ao Logar... verifique as informações"); return;}

    const data = await res.json();
    const token = await data.token;

    localStorage.setItem("token",token);

    if(parseJwt(token).role === "ADMIN"){
        alert("Redimencionando para pagina de Admin...");
        window.location.href="../Admin/admin.html"
    }else{
        alert("Redimencionando para pagina de Usuario...");
        window.location.href="../Usuario/Usuario.html"
    }


})

