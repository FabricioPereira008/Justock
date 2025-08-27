package com.justeam.justock_api.service;

import com.justeam.justock_api.model.user;
import com.justeam.justock_api.repository.userRepository;
import com.justeam.justock_api.request.userCreateRequest;
import com.justeam.justock_api.request.userUpdateRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import jakarta.transaction.Transactional;

@Service
public class userService {

    @Autowired
    private userRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<user> listAllusers() {
        return userRepository.findAll();
    }

    public user finduser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public user createuser(userCreateRequest dto) {
        user user = new user();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole("USER");
        return userRepository.save(user);
    }

    @Transactional
    public user updateuser(Long id, userUpdateRequest dto) {
        user existinguser = userRepository.findById(id).orElse(null);
        if (existinguser != null) {
            existinguser.setName(dto.getName());
            existinguser.setEmail(dto.getEmail());
            if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
                existinguser.setPassword(passwordEncoder.encode(dto.getPassword()));
            }
            return userRepository.save(existinguser);
        }
        return null;
    }

    public boolean deleteuser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public void promoteuser(Long id) {
        userRepository.promote(id);
    }
}