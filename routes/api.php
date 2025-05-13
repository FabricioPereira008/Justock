<?php

use App\Http\Controllers\{
    AuthController,
    UserController,
    ProductController
};
use Illuminate\Support\Facades\Route;

Route::post('/login', [AuthController::class, 'login']);
Route::post('/cadastrar', [UserController::class, 'store']);

Route::middleware('auth:api')->group(function () {
    Route::post('/logout',[AuthController::class, 'logout']);

    Route::prefix('/user')->group(function (){
        Route::get('/', [UserController::class, 'index']);
        Route::get('/visualizar/{id}', [UserController::class, 'show']);
        Route::delete('/deletar/{id}', [UserController::class, 'destroy']);
        Route::put('/atualizar/{id}', [UserController::class, 'update']);
        Route::put('/promote/{id}', [UserController::class, 'makeAdmin']);
    });
    
    Route::prefix('/product')->group(function (){
        Route::get('/', [ProductController::class, 'index']);
        Route::put('/atualizar/{id}', [ProductController::class, 'update']);
        Route::delete('/deletar/{id}', [ProductController::class, 'destroy']);
        Route::get('/visualizar/{id}', [ProductController::class, 'show']);
        Route::post('/cadastrar', [ProductController::class, 'store']);
    });
});

