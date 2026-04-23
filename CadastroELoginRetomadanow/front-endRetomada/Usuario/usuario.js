const API="http://localhost:8080/usuario/me";

function authfetch(url,options={}){
    const token = localStorage.getItem("token")

    if(!token){alert("Usuario não autenticado!!");return;}

    return fetch(url,{
        ...options,
        headers:{
            ...(options),
            "Content-Type":"application/json",
            "Authorization":`Bearer ${token}`
        }
    })
};

async function listarInformacoes() {
    const res = await authfetch(API);

    if(!res.ok){alert("Ocorreu um erro ao buscar as informações..."); return;}
    
    let user = await res.json();

    let lista = `<table border=1><tr><th>Id:</th><th>Email:</th><th>Role:</th><th>Ações</th></tr>
    <tr>
        <td>${user.id}</td>
        <td>${user.email}</td>
        <td>${user.role}</td>
        <td>
            <button type="button" onclick="AtualizarSenha()">Atualizar Senha</button>
            <button type="button" onclick="DeletarLogin()">Deletar Conta</button>
        </td>
    </tr>
    </table>
    `
    
    document.getElementById("lista").innerHTML=lista;

}

async function AtualizarSenha() {
    let html=`<form>
        <label>Senha Atual:</label>
        <input type="password" id="senhaAtual"></input> <br><br>

        <label >Nova Senha:</label>
        <input type="password" id="novaSenha"></input> <br><br>

        <button type="button" onclick="AtualizarSenhaAtual()">Atualizar senha</button>

    </form>`

    document.getElementById("lista").innerHTML = html;
    
}

async function AtualizarSenhaAtual() {

    if(!confirm("Deseja realmente Atualizar sua Senha?")){alert("Atualização de senha cancelada...");listarInformacoes();return;}

    const atual={
        senhaAtual:document.getElementById("senhaAtual").value,
        novaSenha:document.getElementById("novaSenha").value
    }

    if(atual.senhaAtual==="" || atual.novaSenha===""){alert("Campos em branco!!"); return;}

    const res = await authfetch(`${API}/senha`,{
        method:"PUT",
        body:JSON.stringify(atual)
    });

    if(!res.ok){alert("Ocorreu um erro ao atualizar senha"); return}

    alert("Senha Atualizada... redimencionando para pagina de login...");
    document.getElementById("lista").innerHTML="";
    window.location.href="../Login/Login.html"
}

async function DeletarLogin() {
    if(!confirm("Deseja realmente Deletar sua Conta ?")){return}

    const res = await authfetch(API,{method:"DELETE"});

    if(!res.ok){alert("Ocorreu um erro ao deletar o login!");return;}

    alert("Conta deletada Com Sucesso redimencionando para Pagina de Cadastro....");
    document.getElementById("lista").innerHTML="";
    window.location.href="../Cadastro/cadastro.html";
}

