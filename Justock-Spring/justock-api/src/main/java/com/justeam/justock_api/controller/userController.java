package com.justeam.justock_api.controller;

import com.justeam.justock_api.dto.ApiResponseDTO;
import com.justeam.justock_api.dto.UserResponseDTO;
import com.justeam.justock_api.model.User;
import com.justeam.justock_api.request.UserCreateRequest;
import com.justeam.justock_api.request.UserUpdateRequest;
import com.justeam.justock_api.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/User")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    // GET /api/User
    @GetMapping("/")
    public ApiResponseDTO<List<UserResponseDTO>> index() {
        List<UserResponseDTO> users = userService.listAllusers()
                .stream()
                .map(u -> new UserResponseDTO(u.getId(), u.getName(), u.getEmail(), u.getRole()))
                .collect(Collectors.toList());
        return new ApiResponseDTO<>(200, "Usuários encontrados!", users);
    }

    // GET /api/User/visualizar/{id}
    @GetMapping("/visualizar/{id}")
    public ApiResponseDTO<UserResponseDTO> show(@PathVariable Long id) {
        User user = userService.finduser(id);
        if (user == null) {
            return new ApiResponseDTO<>(404, "Usuário não encontrado!", null);
        }
        UserResponseDTO dto = new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());
        return new ApiResponseDTO<>(200, "Usuário encontrado!", dto);
    }

    // POST /api/User/cadastrar
    @PostMapping("/cadastrar")
    public ApiResponseDTO<UserResponseDTO> store(@Valid @RequestBody UserCreateRequest request) {
        User user = userService.createuser(request);
        UserResponseDTO dto = new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());
        return new ApiResponseDTO<>(200, "Usuário cadastrado com sucesso!", dto);
    }

    // PUT /api/User/atualizar/{id}
    @PutMapping("/atualizar/{id}")
    public ApiResponseDTO<UserResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest request) {
        User user = userService.updateuser(id, request);
        if (user == null) {
            return new ApiResponseDTO<>(404, "Usuário não encontrado!", null);
        }
        UserResponseDTO dto = new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());
        return new ApiResponseDTO<>(200, "Usuário atualizado!", dto);
    }

    // DELETE /api/User/deletar/{id}
    @DeleteMapping("/deletar/{id}")
    public ApiResponseDTO<Void> destroy(@PathVariable Long id) {
        boolean deleted = userService.deleteuser(id);
        if (!deleted) {
            return new ApiResponseDTO<>(404, "Usuário não encontrado!", null);
        }
        return new ApiResponseDTO<>(200, "Usuário deletado!", null);
    }

    // PUT /api/User/promote/{id}
    @PutMapping("/promote/{id}")
    public ApiResponseDTO<Void> makeAdmin(@PathVariable Long id) {
        userService.promoteuser(id);
        return new ApiResponseDTO<>(200, "Usuário promovido a administrador com sucesso!", null);
    }
}
