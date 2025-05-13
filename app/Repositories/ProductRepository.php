<?php

namespace App\Repositories;

use App\Models\Product;
use App\Interfaces\ProductRepositoryInterface;

class ProductRepository implements ProductRepositoryInterface
{
    public function getAll()
    {
        return Product::select('id', 'nome', 'preco', 'quantidade')->paginate('10');
    }

    public function findById($id)
    {
        return Product::find($id);
    }

    public function create(array $data)
    {
        return Product::create([
            'nome' => $data['nome'],
            'preco' => $data['preco'],
            'quantidade' => $data['quantidade'],
        ]);
    }

    public function update(array $data, $id)
    {
        $Product = Product::find($id);
        return $Product ? $Product->update($data) : null;
    }

    public function delete($id)
    {
        $Product = Product::find($id);
        return $Product ? $Product->delete() : null;
    }
}