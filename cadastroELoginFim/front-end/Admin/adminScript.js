const API="http://localhost:8080/admin"

function authfetch(url,options={}){
    const token=localStorage.getItem("token")

    if(!token){alert("Não autenticado");window.location.href="../Login/login.html"; return;}

    return fetch(url,{
        ...options,
        headers:{
            ...(options),
            "Authorization":`Bearer ${token}`,
            "Content-Type":"application/json"
        }
    })

};

async function listar() {
    const res = await authfetch(API);

    if(!res.ok){alert("error ao listar Usuarios"); return;}

    const lista = await res.json();

    let listaHtml="<table border=1><tr><th>ID:</th><th>Email:</th><th>Role</th><th>Ação</th></tr>"

    lista.forEach(usuario => {
        listaHtml+=`
        <tr>
            <td>${usuario.id}</td>
            <td>${usuario.email}</td>
            <td>${usuario.role}</td>
            <td>
                <button type="button" onclick="Deletar(${usuario.id})">Deletar</button>
            </td>
        </tr>
        `
    });

    document.getElementById("lista").innerHTML=listaHtml;

}


async function Deletar(id) {
    if(!confirm("Deseja deletar O Usuario Selecionado??")){return;}

    const res=await authfetch(`${API}/${id}`,{method:"DELETE"})

    if(!res.ok){alert("Ocorreu um erro ao deletar o usuario"); return};

    alert("Usuario deletado, resetando tabela");
    listar();
    
}

