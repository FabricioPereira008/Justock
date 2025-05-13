<?php

namespace App\Repositories;

use App\Models\User;
use App\Interfaces\UserRepositoryInterface;

class UserRepository implements UserRepositoryInterface
{
    public function getAll()
    {
        return User::select('id', 'name', 'email', 'role')->paginate(10);
    }

    public function findById($id)
    {
        return User::find($id);
    }

    public function create(array $data)
    {
        return User::create([
            'name' => $data['name'],
            'email' => $data['email'],
            'password' => bcrypt($data['password']),
        ]);
    }

    public function update(array $data, $id)
    {
        $user = User::find($id);
        return $user ? $user->update($data) : null;
    }

    public function delete($id)
    {
        $user = User::find($id);
        return $user ? $user->delete() : null;
    }

    public function promote($id)
    {
        $user = User::find($id);
        $user->role = 'admin';
        $user->save();
    }
}