package com.justeam.justock_api.controller;

import com.justeam.justock_api.dto.apiResponseDTO;
import com.justeam.justock_api.dto.userResponseDTO;
import com.justeam.justock_api.model.user;
import com.justeam.justock_api.request.userCreateRequest;
import com.justeam.justock_api.request.userUpdateRequest;
import com.justeam.justock_api.service.userService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class userController {

    @Autowired
    private userService userService;

    // GET /api/user
    @GetMapping("/")
    public apiResponseDTO<List<userResponseDTO>> index() {
        List<userResponseDTO> users = userService.listAllusers()
                .stream()
                .map(u -> new userResponseDTO(u.getId(), u.getName(), u.getEmail(), u.getRole()))
                .collect(Collectors.toList());
        return new apiResponseDTO<>(200, "Usuários encontrados!", users);
    }

    // GET /api/user/visualizar/{id}
    @GetMapping("/visualizar/{id}")
    public apiResponseDTO<userResponseDTO> show(@PathVariable Long id) {
        user user = userService.finduser(id);
        if (user == null) {
            return new apiResponseDTO<>(404, "Usuário não encontrado!", null);
        }
        userResponseDTO dto = new userResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());
        return new apiResponseDTO<>(200, "Usuário encontrado!", dto);
    }

    // POST /api/user/cadastrar
    @PostMapping("/cadastrar")
    public apiResponseDTO<userResponseDTO> store(@Valid @RequestBody userCreateRequest request) {
        user user = userService.createuser(request);
        userResponseDTO dto = new userResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());
        return new apiResponseDTO<>(200, "Usuário cadastrado com sucesso!", dto);
    }

    // PUT /api/user/atualizar/{id}
    @PutMapping("/atualizar/{id}")
    public apiResponseDTO<userResponseDTO> update(@PathVariable Long id, @Valid @RequestBody userUpdateRequest request) {
        user user = userService.updateuser(id, request);
        if (user == null) {
            return new apiResponseDTO<>(404, "Usuário não encontrado!", null);
        }
        userResponseDTO dto = new userResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());
        return new apiResponseDTO<>(200, "Usuário atualizado!", dto);
    }

    // DELETE /api/user/deletar/{id}
    @DeleteMapping("/deletar/{id}")
    public apiResponseDTO<Void> destroy(@PathVariable Long id) {
        boolean deleted = userService.deleteuser(id);
        if (!deleted) {
            return new apiResponseDTO<>(404, "Usuário não encontrado!", null);
        }
        return new apiResponseDTO<>(200, "Usuário deletado!", null);
    }

    // PUT /api/user/promote/{id}
    @PutMapping("/promote/{id}")
    public apiResponseDTO<Void> makeAdmin(@PathVariable Long id) {
        userService.promoteuser(id);
        return new apiResponseDTO<>(200, "Usuário promovido a administrador com sucesso!", null);
    }
}
