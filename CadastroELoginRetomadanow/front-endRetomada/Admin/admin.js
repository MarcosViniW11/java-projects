const API = "http://localhost:8080/admin"

function authFeltch(url,options={}){
    const token = localStorage.getItem("token");

    if(!token){alert("Usuario não autenticado!"); return;}

    return fetch(url,{
        ...options,
        headers:{
            ...(options),
            "Content-Type":"application/json",
            "Authorization":`Bearer ${token}`
        }
    });
}

async function Listar() {
    const res = await authFeltch(API);

    if(!res.ok){alert("Ocorreu um erro ao listar"); return;}

    const users = await res.json();

    let lista="<table border=1><tr><th>Id:</th><th>Email:</th><th>Role</th><th>Ações</th></tr>"

    users.forEach(user => {

        if(user.id == 1){
            lista+=`
        <tr>
            <td>${user.id}</td>
            <td>${user.email}</td>
            <td>${user.role}</td>
        </tr>
        `
        }else{
          lista+=`
        <tr>
            <td>${user.id}</td>
            <td>${user.email}</td>
            <td>${user.role}</td>
            <td>
                <button type="button" onclick="deletarUser(${user.id})">Deletar</button>
            </td>
        </tr>
        `  
        }

        
    })

    lista+="</table>"
    document.getElementById("lista").innerHTML = lista;

}

async function deletarUser(id) {
    if(!confirm("Deseja Deletar O Usuario selecionado?"))return;

    const res = await authFeltch(`${API}/${id}`,{method:"DELETE"});

    if(res.status!==204){alert("Ocorreu um erro ao deletar o usuario selecionado!!"); return;}

    alert("Usuario Deletado com sucesso!!");
    document.getElementById("lista").innerHTML = "";
}


