const API="http://localhost:8080/usuarios/me"





function authfetch(url, options={}){
    const token=localStorage.getItem("token")

    if(!token){
        alert("Usuario não autenticado, redimencionando para pagina login...");
        window.location.href="../Login/login.html";
        return;
    }

    return fetch(url,{
        ...options,
        headers:{
            ...(options.headers || {}),
            "Authorization":`Bearer ${token}`,
            "Content-Type":"application/json"
        }
    });

}

document.getElementById("botaoInformacoes").addEventListener("click", async (pagina)=>{
    const res=await authfetch(API);

    if(!res.ok){alert("Ocorru um erro ao buscar as informações!");return;}

    const usuario=await res.json();

    let table=`<table border=1>
        <tr>
            <th>ID:</th>
            <th>Email:</th>
            <th>Role:</th>
            <th>Ações:</th>
        </tr>

        <tr>
            <td>${usuario.id}</td>
            <td>${usuario.email}</td>
            <td>${usuario.role}</td>
            <td>
                <button type="button" id="botaoDeletar" onclick="Deletar()">Deletar</button>
                <button type="button" id="botaoEditar" onclick="Atualizar()">Editar Senha</button>
            </td>
        </tr>

    </table>`;

    document.getElementById("lista-informações").innerHTML=table;

});

async function Atualizar() {

    let form=`
        <form>
            <label for="senhaAtual" >Senha Atual:</label>
            <input type="password" id="senhaAtual"></input><br><br>

            <label for="novaSenha" >Nova Senha:</label>
            <input type="password" id="novaSenha"></input><br><br>

            <button type="button" id="botaoAtualizarSenha" onclick="AtualizarSenha()">Atualizar Senha</button>
        </form>
    
    `

    document.getElementById("lista-informações").innerHTML=form;

    
}

async function AtualizarSenha() {
    const atualizar={
        senhaAtual:document.getElementById("senhaAtual").value,
        novaSenha:document.getElementById("novaSenha").value
    }

    if(atualizar.senhaAtual==="" || atualizar.novaSenha===""){alert("não é possivel atualizar senha vazia");return;}

    const res=await authfetch(`${API}/senha`,{
        method:"PUT",
        body:JSON.stringify(atualizar)
    });

    if(!res.ok || res.status!==204){alert("Ocorreu um erro ao atualizar a senha"); return;}

    alert("Senha atualizada com sucesso, Redimencionando para a pagina de Login");
    document.getElementById("lista-informações").innerHTML="";
    window.location.href="../Login/login.html";
    
}

async function Deletar() {
    if(!confirm("Deseja Realmente deletar Seu Perfil??")) return;

    const res=await authfetch(API,{method:"DELETE"})

    if(!res.ok || res.status!==204){alert("Ocorreu um erro ao deletar seu perfil!!"); return;}

    alert("Perfim Deletado Com sucesso, Redimendionando para a pagina de Cadastro");
    window.location.href="../Cadastro/cadastro.html";
    
}



