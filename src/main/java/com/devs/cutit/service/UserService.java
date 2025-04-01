package com.devs.cutit.service;

import com.devs.cutit.DTO.CreateUserDTO;
import com.devs.cutit.model.RequestPartModel;
import com.devs.cutit.model.UserModel;
import com.devs.cutit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel createUser(CreateUserDTO user) {
        UserModel userModel = UserModel.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .status("ACTIVE") // Estado predeterminado
                .build();
        return userRepository.save(userModel);
    }

    public UserModel getUser(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }
}