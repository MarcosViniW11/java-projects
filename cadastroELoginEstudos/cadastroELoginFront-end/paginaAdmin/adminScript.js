const API = "http://localhost:8080/admin";


function authfetch(url, options = {}) {
    const token = localStorage.getItem("token")

    if (!token) {
        alert("Usuario Não autenticado!!");
        window.location.href = "../Login/login.html";
        return;
    }

    return fetch(url, {
        ...options,
        headers: {
            ...(options.headers || {}),
            "Authorization": `Bearer ${token}`,
            "Content-Type": "application/json"
        }
    });

}

document.getElementById("botaoListar").addEventListener("click", async (pagina) => {
    const res = await authfetch(API)

    if (!res.ok) { alert("Ocorreu um erro ao listar os usuario"); return; }

    const lista = await res.json();

    let html = "<table border=1><tr><th>ID:</th><th>Email:</th><th>Role:</th><th>Ação</th></tr>"

    lista.forEach(usuario => {
        if (usuario.id === 1) {
            html += `
            <tr>
                <td>${usuario.id}</td>
                <td>${usuario.email}</td>
                <td>${usuario.role}</td>
            </tr>
            `
        } else {
            html += `
        <tr>
            <td>${usuario.id}</td>
            <td>${usuario.email}</td>
            <td>${usuario.role}</td>
            <td>
                <button type="button" onclick="deletarPorId(${usuario.id})">Deletar</button>
            </td>
        </tr>
        `
        }



    });

    html += "</table>"
    document.getElementById("lista-Usuarios").innerHTML = html;
});

async function deletarPorId(id) {
    if (!confirm("DESEJA REALMENTE DELETAR O USUARIO SELECIONADO??")) { return; }

    const res = await authfetch(`${API}/${id}`, { method: "DELETE" })

    if (!res.ok || !res.status === 204) { alert("Ocorreu um erro ao deletar o usuario selecionado!!"); return; };

    alert("Usuario deletado Com Sucesso!")
    document.getElementById("lista-Usuarios").innerHTML = "";
}
