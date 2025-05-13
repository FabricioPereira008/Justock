<?php

namespace App\Http\Controllers;
use App\Http\Requests\UserCreateRequest;
use App\Http\Requests\UserUpdateRequest;
use App\Models\User;
use App\Interfaces\UserServiceInterface;
use Illuminate\Http\Request;
use App\Http\Traits\CheckAdmin;
use Illuminate\Support\Facades\Auth;

class UserController extends Controller
{
    use CheckAdmin;
    protected $userService;

    public function __construct(UserServiceInterface $userService)
    {
        $this->userService = $userService;
    }

    public function index()
    {
        $user = $this->userService->listAllUsers();

        return [
            'status' => 200,
            'menssagem' => 'Usuários encontrados!!',
            'user' => $user
        ];
    }

    public function store(UserCreateRequest $request)
    {
        $user = $this->userService->createUser($request->all());

        return [
            'status' => 200,
            'menssagem' => 'Usuário cadastrado com sucesso!!',
            'user' => $user
        ];
    }

    public function show(string $id)
    {
        $user = $this->userService->findUser($id);

        if(!$user){
            return [
                'status' => 404,
                'message' => 'Usuário não encontrado!',
                'user' => $user
            ];
        }

        return [
            'status' => 200,
            'message' => 'Usuário encontrado!!',
            'user' => $user
        ];
    }

    public function update(Request $request, string $id)
    {
        if (!$this->isAdmin()) {
            return response()->json([
                'status' => 403,
                'message' => 'Acesso negado! Somente administradores podem realizar essa ação.'
            ], 403);
        }

        $user = $this->userService->updateUser($request->all(), $id);

        if(!$user){
            return [
                'status' => 404,
                'message' => 'Usuário não encontrado!',
                'user' => $user
            ];
        }

        return [
            'status' => 200,
            'message' => 'Usuário atualizado!!',
            'user' => $user
        ];
    }

    public function destroy(string $id)
    {
        if (!$this->isAdmin()) {
            return response()->json([
                'status' => 403,
                'message' => 'Acesso negado! Somente administradores podem realizar essa ação.'
            ], 403);
        }

        $deleted = $this->userService->deleteUser($id);

        if(!$deleted){
            return [
                'status' => 404,
                'message' => 'Usuário não encontrado!',
                'user' => $deleted
            ];
        }

        return [
            'status' => 200,
            'message' => 'Usuário deletado!!'
        ];

    }

    public function makeAdmin($id){
    if (!Auth::check() || Auth::user()->role !== 'admin') {
        return response()->json([
            'status' => 403,
            'message' => 'Acesso negado! Somente administradores podem realizar essa ação.'
        ], 403);
    }

    $user = $this->userService->promoteUser($id);

    return response()->json([
        'status' => 200,
        'message' => 'Usuário promovido a administrador com sucesso.'
    ]);
}

}
