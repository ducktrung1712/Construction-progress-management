package com.tland.landsystem.service;

import com.tland.landsystem.entity.Users;
import com.tland.landsystem.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Optional<Users> getUserById(Integer id) {
        return usersRepository.findById(id);
    }

    public boolean existsByEmail(String email) {
        return usersRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return usersRepository.existsByUsername(username);
    }

    public Users saveUser(Users user) {
        return usersRepository.save(user);
    }

    public Optional<Users> updateUser(Integer id, Users userDetails) {
        return usersRepository.findById(id).map(user -> {
            user.setFullName(userDetails.getFullName());
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            user.setRole(userDetails.getRole());
            user.setWorkGroup(userDetails.getWorkGroup());
            return usersRepository.save(user);
        });
    }

    public boolean deleteUser(Integer id) {
        return usersRepository.findById(id).map(user -> {
            usersRepository.delete(user);
            return true;
        }).orElse(false);
    }
}
