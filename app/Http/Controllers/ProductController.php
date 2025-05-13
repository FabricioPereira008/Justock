<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Product;
use App\Interfaces\ProductServiceInterface;
use App\Http\Requests\ProductCreateRequest;
use App\Http\Requests\ProductUpdateRequest;

class ProductController extends Controller
{
    protected $ProductService;

    public function __construct(ProductServiceInterface $ProductService)
    {
        $this->ProductService = $ProductService;
    }

    public function store(ProductCreateRequest $request)
    {
        $product = $this->ProductService->createProduct($request->all());

        return [
            'status' => 200,
            'menssagem' => 'Produto cadastrado com sucesso!!',
            'product' => $product
        ];
    }

    public function index()
    {
        $product = $this->ProductService->listAllProducts();

        return [
            'status' => 200,
            'menssagem' => 'Produtos encontrados!!',
            'product' => $product
        ];
    }

    public function show(string $id)
    {
        $product = $this->ProductService->findProduct($id);

        if(!$product){
            return [
                'status' => 404,
                'message' => 'Produto não encontrado!',
                'product' => $product
            ];
        }

        return [
            'status' => 200,
            'message' => 'Produto encontrado!!',
            'product' => $product
        ];
    }

    public function update(ProductUpdateRequest $request, string $id)
    {
        $product = $this->ProductService->updateproduct($request->all(), $id);

        if(!$product){
            return [
                'status' => 404,
                'message' => 'Produto não encontrado!',
                'product' => $product
            ];
        }

        return [
            'status' => 200,
            'message' => 'Produto atualizado!!',
            'product' => $product
        ];
    }

    public function destroy(string $id)
    {
        $deleted = $this->ProductService->deleteproduct($id);

        if(!$deleted){
            return [
                'status' => 404,
                'message' => 'Produto não encontrado!',
                'product' => $deleted
            ];
        }

        return [
            'status' => 200,
            'message' => 'Produto deletado!!'
        ];

    }
}
