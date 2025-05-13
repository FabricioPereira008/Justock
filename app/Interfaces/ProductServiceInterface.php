<?php

namespace App\Interfaces;

interface ProductServiceInterface
{
    public function listAllProducts();
    public function findProduct($id);
    public function createProduct(array $data);
    public function updateProduct(array $data, $id);
    public function deleteProduct($id);
}