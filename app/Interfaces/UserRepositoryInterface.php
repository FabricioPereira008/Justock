<?php

namespace App\Interfaces;

interface UserRepositoryInterface
{
    public function getAll();
    public function findById($id);
    public function create(array $data);
    public function update(array $data, $id);
    public function delete($id);
    public function promote($id);
}
