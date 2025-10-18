package com.justeam.justock_api.service;

import com.justeam.justock_api.model.User;
import com.justeam.justock_api.repository.UserRepository;
import com.justeam.justock_api.request.UserCreateRequest;
import com.justeam.justock_api.request.UserUpdateRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> listAllusers() {
        return userRepository.findAll();
    }

    public User finduser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createuser(UserCreateRequest dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        if ("admin".equalsIgnoreCase(dto.getRole())) {
            user.setRole("ADMIN");
        } else {
            user.setRole("USER");
        }
        return userRepository.save(user);
    }

    @Transactional
    public User updateuser(Long id, UserUpdateRequest dto) {
        User existinguser = userRepository.findById(id).orElse(null);
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