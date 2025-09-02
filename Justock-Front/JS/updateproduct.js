document.addEventListener("DOMContentLoaded", async function () {
    const productId = new URLSearchParams(window.location.search).get('id');
    const editProductForm = document.getElementById('editProductForm');
    const mensagem = document.getElementById('mensagem');

    async function carregarProduto() {
        try {
            const response = await fetch(`http://localhost:8000/api/product/visualizar/${productId}`, {
                headers: {
                    'Authorization': 'Bearer ' + localStorage.getItem('token')
                }
            });
            const data = await response.json();

            if (data.status === 200 && data.product) {
                document.getElementById('nome').value = data.product.nome;
                document.getElementById('preco').value = data.product.preco;
                document.getElementById('quantidade').value = data.product.quantidade;
            } else {
                mensagem.textContent = 'Erro ao carregar o produto.';
                mensagem.classList.add('alert-danger');
            }
        } catch (error) {
            console.error("Erro ao carregar o produto:", error);
            mensagem.textContent = 'Erro ao carregar o produto. Tente novamente.';
            mensagem.classList.add('alert-danger');
        }
    }

    editProductForm.addEventListener('submit', async function (event) {
        event.preventDefault();

        const nome = document.getElementById('nome').value;
        const preco = document.getElementById('preco').value;
        const quantidade = document.getElementById('quantidade').value;

        try {
            const response = await fetch(`http://localhost:8000/api/product/atualizar/${productId}`, {
                method: 'PUT',
                headers: {
                    'Authorization': 'Bearer ' + localStorage.getItem('token'),
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ nome, preco, quantidade })
            });

            const data = await response.json();
            console.log("Resposta da API:", data);

            if (data.status === 200) {
                mensagem.textContent = 'Produto atualizado com sucesso!';
                mensagem.classList.add('alert-success');
            } else {
                throw new Error(data.message || 'Erro ao atualizar o produto.');
            }
        } catch (error) {
            console.error("Erro ao atualizar o produto:", error);
            mensagem.textContent = 'Erro ao atualizar o produto. Tente novamente.';
            mensagem.classList.add('alert-danger');
        }
    });

    document.getElementById('cancelButton').addEventListener('click', function () {
        window.location.href = 'products.html';
    });

    carregarProduto();
});
