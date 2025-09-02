document.addEventListener("DOMContentLoaded", function() {
    const mensagem = document.getElementById('mensagem');
    
    if (!localStorage.getItem('token')) {
        mensagem.textContent = 'Por favor, faça login para acessar o dashboard.';
        setTimeout(() => {
            window.location.href = 'index.html';
        }, 2000);
        return;
    }

    document.getElementById('viewUsersButton').addEventListener('click', function() {
        window.location.href = 'users.html';
    });

    document.getElementById('viewProductsButton').addEventListener('click', function() {
        window.location.href = 'products.html';
    });

    document.getElementById('logoutButton').addEventListener('click', function() {
        localStorage.removeItem('token');
        localStorage.removeItem('userId');
        
        mensagem.textContent = 'Logout realizado com sucesso. Redirecionando para a página de login...';
        
        setTimeout(() => {
            window.location.href = 'index.html';
        }, 2000);
    });
});
