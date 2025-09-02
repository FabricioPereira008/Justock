document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('loginForm');
    const mensagem = document.getElementById('mensagem');

    form.addEventListener('submit', function(e) {
        e.preventDefault();

        const loginData = {
            email: document.getElementById('email').value,
            password: document.getElementById('password').value
        };

        fetch('http://localhost:8000/api/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginData)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro na autenticação. Verifique suas credenciais.');
            }
            return response.json();
        })
        .then(data => {
            if (data.status === 200) {
                localStorage.setItem('token', data.usuario.token);
                localStorage.setItem('userId', data.usuario.id);
                mensagem.textContent = `Bem-vindo, ${data.usuario.name}! Login realizado com sucesso.`;
                window.location.href = 'dashboard.html';
            } else {
                mensagem.textContent = 'Erro no login: ' + data.message;
            }
        })
        .catch(error => {
            console.error("Erro:", error);
            mensagem.textContent = 'Erro ao realizar o login. Tente novamente.';
        });
    });
});
