const API="http://localhost:8080/admin"

function authfetch(url,options={}) {
    const token=localStorage.getItem("token");

    if(!token){
        alert("Usuario não autenticado!!");
        window.location.href="../Login/index.html"
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

document.getElementById("botaoListar").addEventListener("click", async (pagina) =>{ 
    pagina.preventDefault();


    const res = await authfetch(API);

    if(!res.ok){alert("Ocorreu um erro ao listar o registro!!"); return;}

    const lista=await res.json();

    let html="<table border=1><tr><th>Id:</th><th>Email:</th><th>Role:</th><th>Ação:</th></tr>"

    lista.forEach(usuario=>{
        if(usuario.id==1){
            html+=`
            <tr>
            <td>${usuario.id}</td>
            <td>${usuario.email}</td>
            <td>${usuario.role}</td>
        </tr>
            `
        }else{
            html+=`
                <tr>
                    <td>${usuario.id}</td>
                    <td>${usuario.email}</td>
                    <td>${usuario.role}</td>
                    <td>
                        <button type="button" onclick="deletarUsuario(${usuario.id})">Deletar</button>
                    </td>
                </tr>
                `
        }

        
    });

    html+="</table>"
    document.getElementById("listaId").innerHTML=html;

})

async function deletarUsuario(id) {
    if(!confirm("deseja deletar o usuario selecionado??")){return;}

    const res= await authfetch(`${API}/${id}`,{method:"DELETE"});

    if(!res.ok){
        alert("Ocorreu um erro ao deletar o usuario selecionado!!");
        return
    }

    alert("Usuario deletado Com sucesso!!");

    document.getElementById("listaId").innerHTML="";

    
}


