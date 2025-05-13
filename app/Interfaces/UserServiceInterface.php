<?php

namespace App\Interfaces;

interface UserServiceInterface
{
    public function listAllUsers();
    public function findUser($id);
    public function createUser(array $data);
    public function updateUser(array $data, $id);
    public function deleteUser($id);
    public function promoteUser($id);
}