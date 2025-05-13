<?php
namespace App\Http\Traits;

use Illuminate\Support\Facades\Auth;

trait CheckAdmin
{
    public function isAdmin()
    {
        return Auth::check() && Auth::user()->role === 'admin';
    }
}
