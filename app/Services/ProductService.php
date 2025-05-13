<?php

namespace App\Services;

use App\Interfaces\ProductServiceInterface;
use App\Interfaces\ProductRepositoryInterface;

class ProductService implements ProductServiceInterface
{
    protected $ProductRepository;

    public function __construct(ProductRepositoryInterface $ProductRepository)
    {
        $this->ProductRepository = $ProductRepository;
    }

    public function listAllProducts()
    {
        return $this->ProductRepository->getAll();
    }

    public function findProduct($id)
    {
        return $this->ProductRepository->findById($id);
    }

    public function createProduct(array $data)
    {
        return $this->ProductRepository->create($data);
    }

    public function updateProduct(array $data, $id)
    {
        return $this->ProductRepository->update($data, $id);
    }

    public function deleteProduct($id)
    {
        return $this->ProductRepository->delete($id);
    }
}