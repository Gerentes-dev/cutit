package com.devs.cutit.repository;

import com.devs.cutit.DTO.CreateUserDTO;
import com.devs.cutit.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
}
