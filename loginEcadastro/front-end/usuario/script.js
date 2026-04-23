const API="http://localhost:8080/usuario"


function authfetch(url,options={}){

    const token=localStorage.getItem("token");

    if(!token){
        alert("Usuario não autencidado");
        window.location.href="../Login/index.html"
        return;
    }

    return fetch(url, {
        ...options,
        headers:{
            ...(options.headers || {}),
            "Authorization" : `Bearer ${token}`,
            "Content-Type" : "application/json"
        }
    })

}

async function mostrarDados() {

    const res= await authfetch(`${API}/me`);

    if(!res.ok){alert("Ocorreu um erro ao pegar as informações!!");return;}

    const usuario = await res.json();
    
    let lista=`<table border=1><tr><th>ID:</th><th>Email:</th><th>Role:</th><th>Ações:</th></tr>
    
    <tr>
        <td>${usuario.id}</td>
        <td>${usuario.email}</td>
        <td>${usuario.role}</td>
        <td>
            <button type="button" onclick="atualizar()" >Atualizar Senha</button>
            <button type="button" onclick="deletar()" >Deletar Usuario</button>
        </td>
    </tr>

    </table>
    `

    document.getElementById("dadosUsuario").innerHTML=lista;   
}

async function atualizar() {
    let form=`<form id="form">
        <label>Senha Atual:</label>
        <input type="password" id="antigaSenha"></input><br><br>

        <label>Senha Nova:</label>
        <input type="password" id="novaSenha"></input><br><br>

        <button type="button" onclick="AtualizarSenha()">Atualizar</button>
    </form>`

    document.getElementById("dadosUsuario").innerHTML=form;
   
}

async function AtualizarSenha() {

    senhaAtualizar={
        senhaAtual:document.getElementById("antigaSenha").value,
        novaSenha:document.getElementById("novaSenha").value
    }

    const res=await authfetch(`${API}/me/senha`,{
        method:"PUT",
        body:JSON.stringify(senhaAtualizar)
    });

    if(res.status!==204){alert("Ocorreu um erro ao atualizar a senha do usuario!"); return;}

    alert("senha atualizada com sucesso, redimencionando para login novamente!!");
    document.getElementById("dadosUsuario").innerHTML="";
    window.location.href="../Login/index.html";
}

async function deletar() {

    if(!confirm("deseja realmente deletar o usuario selecionado?")) return;

    const res = await authfetch(`${API}/me`,{
        method:"DELETE"
    });

    if(!res.ok){
        alert("Ocorreu um erro ao deletar o usuario!!");
        return;
    }

    alert("Usuario Deletado com sucesso, redimencionando para pagina de cadastro...");

    window.location.href="../Cadastro/index.html";
    
}


