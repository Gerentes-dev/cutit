package com.devs.cutit.service;

import com.devs.cutit.DTO.CreateRequestPartDTO;
import com.devs.cutit.DTO.CreateUserDTO;
import com.devs.cutit.model.PartModel;
import com.devs.cutit.model.RequestPartModel;
import com.devs.cutit.model.UserModel;
import com.devs.cutit.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class UserTests {

    private UserService userService;
    @Mock
    private UserRepository userRepositoryMock;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepositoryMock);
    }

    @Test
    void When_CreateUser_Expect_CreateUser_In_BD() {
        UUID userId = UUID.randomUUID();
        CreateUserDTO userDTO = CreateUserDTO.builder()
                .username("jei")
                .password("jei123")
                .status("ACTIVE")
                .build();
        UserModel userModel = UserModel.builder()
                .id(userId)
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .status("ACTIVE") // Estado predeterminado
                .build();

        given(userRepositoryMock.save(userModel)).willReturn(userModel);

        userService.createUser(userDTO);
    }
}