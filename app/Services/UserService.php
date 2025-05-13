<?php

namespace App\Services;

use App\Interfaces\UserServiceInterface;
use App\Interfaces\UserRepositoryInterface;

class UserService implements UserServiceInterface
{
    protected $userRepository;

    public function __construct(UserRepositoryInterface $userRepository)
    {
        $this->userRepository = $userRepository;
    }

    public function listAllUsers()
    {
        return $this->userRepository->getAll();
    }

    public function findUser($id)
    {
        return $this->userRepository->findById($id);
    }

    public function createUser(array $data)
    {
        return $this->userRepository->create($data);
    }

    public function updateUser(array $data, $id)
    {
        return $this->userRepository->update($data, $id);
    }

    public function deleteUser($id)
    {
        return $this->userRepository->delete($id);
    }

    public function promoteUser($id)
    {
        return $this->userRepository->promote($id);
    }
}