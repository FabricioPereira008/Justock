document.addEventListener("DOMContentLoaded", function () {
    const mensagem = document.getElementById('mensagem');
    const editarUsuarioForm = document.getElementById('editarUsuarioForm');

    const urlParams = new URLSearchParams(window.location.search);
    const userId = urlParams.get('id');

    if (userId) {
        carregarUsuario(userId);
    } else {
        mensagem.textContent = 'Usuário não encontrado.';
        mensagem.classList.add('alert-danger');
    }

    async function carregarUsuario(userId) {
        fetch(`http://localhost:8080/api/user/visualizar/${userId}`)
        .then(response => response.json())
        .then(data => {
            console.log("Dados do usuário: ", data);

            if(data.status === 200 && data.data) {
                document.getElementById('editarid').value = data.data.id;
                document.getElementById('editarname').value = data.data.name;
                document.getElementById('editaremail').value = data.data.email;
            } else {
                mensagem.textContent = 'Erro ao carregar os dados do usuário.';
                mensagem.classList.add('alert-danger');
            }
        })
        .catch(error => {
            console.error("Erro ao carregar usuário: ", error);
            mensagem.textContent = 'Erro ao carregar o usuário. Tente novamente.';
        });
        
        /* try {
            const response = await fetch(`http://localhost:8000/api/user/visualizar/${userId}`, {
                method: 'GET',
                headers: {
                    'Authorization': 'Bearer ' + localStorage.getItem('token'),
                    'Content-Type': 'application/json'
                }
            });

            const data = await response.json();
            console.log("Dados do usuário:", data); 
            if (data.status === 200) {
                document.getElementById('editarid').value = data.user.id;
                document.getElementById('editarname').value = data.user.name;
                document.getElementById('editaremail').value = data.user.email;
            } else {
                mensagem.textContent = 'Erro ao carregar os dados do usuário.';
                mensagem.classList.add('alert-danger');
            }
        } catch (error) {
            console.error('Erro ao carregar o usuário:', error);
            mensagem.textContent = 'Erro ao carregar o usuário.';
            mensagem.classList.add('alert-danger');
        } */
    }

    editarUsuarioForm.addEventListener('submit', async function (e) {
        e.preventDefault();
        console.log("Formulário enviado");
        await salvarUsuarioEditado();
    });

    document.getElementById('backToUserList').addEventListener('click', function () {
        window.location.href = 'users.html';
    });

    async function salvarUsuarioEditado() {
        const userId = document.getElementById('editarid').value;
        const name = document.getElementById('editarname').value;
        const email = document.getElementById('editaremail').value;

        if (!name || !email) {
            mensagem.textContent = 'Preencha todos os campos.';
            mensagem.classList.add('alert-danger');
            return;
        }

        const dataToUpdate = { name, email };

        fetch(`http://localhost:8080/api/user/atualizar/${userId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dataToUpdate)
            })
            .then(response => response.json())
            .then(data => {
                console.log("Resposta da API: ", data);
                if (data.status === 200) {
                    mensagem.textContent = 'Usuário atualizado com sucesso!';
                    mensagem.classList.remove('alert-danger');
                    mensagem.classList.add('alert-success');
                    setTimeout(() => {
                    window.location.href = 'users.html';
                }, 1500);
                } else {
                        mensagem.textContent = 'Erro ao atualizar o usuário: ' + data.message;
                        mensagem.classList.add('alert-danger');
                    }
            })
            .catch(error => {
                console.error("Erro ao atualizar o usuário", error);
                mensagem.textContent = 'Erro ao atualizar o usuário. Tente novamente.'
            })
        /* try {
            const response = await fetch(`http://localhost:8000/api/user/atualizar/${userId}`, {
                method: 'PUT',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(dataToUpdate)
            });

            const data = await response.json();
            console.log("Resposta da atualização:", data);
            if (data.status === 200) {
                mensagem.textContent = 'Usuário atualizado com sucesso!';
                mensagem.classList.remove('alert-danger');
                mensagem.classList.add('alert-success');
                setTimeout(() => {
                    window.location.href = 'users.html';
                }, 1500);
            } else {
                mensagem.textContent = 'Erro ao atualizar o usuário: ' + data.message;
                mensagem.classList.add('alert-danger');
            }
        } catch (error) {
            console.error('Erro ao salvar as alterações do usuário:', error);
            mensagem.textContent = 'Erro ao atualizar o usuário.';
            mensagem.classList.add('alert-danger');
        } */
    }
});
