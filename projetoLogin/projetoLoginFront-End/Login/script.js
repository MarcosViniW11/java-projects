const API="http://localhost:8080/auth/login";

document.getElementById("form-login").addEventListener("submit", async(pagina) => {
    pagina.preventDefault();

    const usuario={
        email:document.getElementById("emailId").value,
        senha:document.getElementById("senhaId").value
    };

    if(usuario.email==="" || usuario.senha===""){alert("Não se pode logar com campo(s) em branco(s)");return;}

    const res = await fetch(`${API}`,{
        method:"POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify(usuario)
    });
    
    if(!res.ok){alert("Ocorreu um erro ao gerar o toker!! (senha/email) invalidos"); return;}

    const token = await res.text();

    localStorage.setItem("token",token);

    window.location.href="../Cliente/index.html"

})

