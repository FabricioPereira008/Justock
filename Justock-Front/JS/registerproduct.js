document.addEventListener("DOMContentLoaded", function () {
    const addProductForm = document.getElementById('addProductForm');
    const mensagem = document.getElementById('mensagem');

    addProductForm.addEventListener('submit', async function (e) {
        e.preventDefault();
        await adicionarProduto();
    });

    document.getElementById('backToProductList').addEventListener('click', function () {
        window.location.href = 'products.html';
    });

    async function adicionarProduto() {
        const nome = document.getElementById('nome').value;
        const preco = parseFloat(document.getElementById('preco').value);
        const quantidade = parseInt(document.getElementById('quantidade').value, 10);

        const dataToSend = { nome, preco, quantidade };

        try {
            const response = await fetch('http://localhost:8000/api/product/cadastrar', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + localStorage.getItem('token')
                },
                body: JSON.stringify(dataToSend)
            });

            const data = await response.json();
            console.log("Status da Resposta:", response.status);
            console.log("Resposta da API:", data);

            if (response.status === 200 && data.status === 200) {
                mensagem.textContent = 'Produto adicionado com sucesso!';
                mensagem.classList.remove('alert-danger');
                mensagem.classList.add('alert-success');
                addProductForm.reset();
            } else {
                throw new Error(data.message || 'Erro ao adicionar o produto.');
            }
        } catch (error) {
            console.error("Erro ao adicionar produto:", error);
            mensagem.textContent = 'Erro ao adicionar produto. Tente novamente.';
            mensagem.classList.remove('alert-success');
            mensagem.classList.add('alert-danger');
        }
    }
});
