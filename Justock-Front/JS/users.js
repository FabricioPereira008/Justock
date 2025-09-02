document.addEventListener("DOMContentLoaded", function () {
    const userList = document.getElementById('userList');
    const mensagem = document.getElementById('mensagem');

    function carregarUsuarios() {
        fetch('http://localhost:8080/api/user/')
        .then(response => response.json())
        .then(data => {
            console.log("Resposta da API:", data);

            if (data.status === 200 && Array.isArray(data.data)) {
                userList.innerHTML = '';
                data.data.forEach(user => {
                    const userItem = document.createElement('li');
                    userItem.classList.add('user-item');
                    userItem.innerHTML = `
                        <span><strong>Nome:</strong> ${user.name}</span>
                        <span><strong>Email:</strong> ${user.email}</span>
                        <span><strong>Admin:</strong> ${user.role.toLowerCase() === 'admin' ? 'Sim' : 'Não'}</span>
                        <button class="edit-button" onclick="editarUsuario(${user.id})">Editar</button>
                        <button class="delete-button" onclick="excluirUsuario(${user.id})">Excluir</button>
                        ${user.role.toLowerCase() !== 'admin' ? `<button class="promote-button" onclick="promoverUsuario(${user.id})">Promover a Admin</button>` : ''}
                    `;
                    userList.appendChild(userItem);
                });
                
            } else {
                throw new Error("Formato inesperado de resposta da API.");
            }
        })
        .catch(error => {
            console.error("Erro ao carregar os usuários:", error);
            mensagem.textContent = 'Erro ao carregar os usuários. Tente novamente.';
        });
    }

    window.excluirUsuario = function (id) {
        if (confirm("Tem certeza que deseja excluir este usuário?")) {
            fetch(`http://localhost:8080/api/user/deletar/${id}`, {
                method: 'DELETE'
            })
            .then(response => response.json())
            .then(data => {
                if (data.status === 200) {
                    carregarUsuarios();
                    mensagem.textContent = 'Usuário excluído com sucesso!';
                } else {
                    throw new Error(data.message || 'Erro ao excluir o usuário.');
                }
            })
            .catch(error => {
                console.error("Erro ao excluir o usuário:", error);
                mensagem.textContent = 'Erro ao excluir o usuário. Tente novamente.';
            });
        }
    };
    

    window.promoverUsuario = function (id) {
        if (confirm("Tem certeza que deseja promover este usuário a administrador?")) {
            fetch(`http://localhost:8080/api/user/promote/${id}`, {
                method: 'PUT'
            })
            .then(response => response.json())
            .then(data => {
                if (data.status === 200) {
                    carregarUsuarios();
                    mensagem.textContent = 'Usuário promovido a administrador com sucesso!';
                } else {
                    throw new Error(data.message || 'Erro ao promover o usuário.');
                }
            })
            .catch(error => {
                console.error("Erro ao promover o usuário:", error);
                mensagem.textContent = 'Erro ao promover o usuário. Tente novamente.';
            });
        }
    };
    

    window.editarUsuario = function (userId) {
        window.location.href = `updateuser.html?id=${userId}`;
    };

    document.getElementById('backToDashboard').addEventListener('click', function() {
        window.location.href = 'dashboard.html';
    });

    carregarUsuarios();
});
