document.addEventListener("DOMContentLoaded", function () {
    const productList = document.getElementById('productList');
    const mensagem = document.getElementById('mensagem');

    document.getElementById('addProductButton').addEventListener('click', function () {
        window.location.href = 'registerproduct.html';
    });
    
    function carregarProdutos() {
        fetch('http://localhost:8000/api/product/', {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            }
        })
        .then(response => response.json())
        .then(data => {
            console.log("Resposta da API:", data);

            if (data.status === 200 && data.product && Array.isArray(data.product.data)) {
                productList.innerHTML = '';
                data.product.data.forEach(produto => {
                    const productItem = document.createElement('li');
                    productItem.classList.add('product-item');
                    productItem.innerHTML = `
                        <span><strong>Nome:</strong> ${produto.nome}</span>
                        <span><strong>Preço:</strong> ${produto.preco}</span>
                        <span><strong>Quantidade:</strong> ${produto.quantidade}</span>
                        <button class="edit-button" onclick="editarProduto(${produto.id})">Editar</button>
                        <button class="delete-button" onclick="excluirProduto(${produto.id})">Excluir</button>
                    `;
                    productList.appendChild(productItem);
                });
                
            } else {
                throw new Error("Formato inesperado de resposta da API.");
            }
        })
        .catch(error => {
            console.error("Erro ao carregar os produtos:", error);
            mensagem.textContent = 'Erro ao carregar os produtos. Tente novamente.';
            mensagem.classList.add('alert-danger');
        });
    }

    window.excluirProduto = function (id) {
        if (confirm("Tem certeza que deseja excluir este produto?")) {
            fetch(`http://localhost:8000/api/product/deletar/${id}`, {
                method: 'DELETE',
                headers: {
                    'Authorization': 'Bearer ' + localStorage.getItem('token')
                }
            })
            .then(response => response.json())
            .then(data => {
                if (data.status === 200) {
                    carregarProdutos();
                    mensagem.textContent = 'Produto excluído com sucesso!';
                } else {
                    throw new Error(data.mensagem || 'Erro ao excluir o produto.');
                }
            })
            .catch(error => {
                console.error("Erro ao excluir o produto:", error);
                mensagem.textContent = 'Erro ao excluir o produto. Tente novamente.';
            });
        }
    };

    window.editarProduto = function (productId) {
        window.location.href = `updateproduct.html?id=${productId}`;
    };

    document.getElementById('backToDashboard').addEventListener('click', function() {
        window.location.href = 'dashboard.html';
    });

    carregarProdutos();
});
