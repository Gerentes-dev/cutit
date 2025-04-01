package com.devs.cutit.controller;

import com.devs.cutit.DTO.CreateUserDTO;
import com.devs.cutit.model.UserModel;
import com.devs.cutit.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@Tag(name = "User")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }

    //@GetMapping("/search")
    //public UserModel searchPart(@RequestParam UUID id) {
      //  return userService.getUser(id);
    //}

    @PostMapping("/create")
    public UserModel createPart(@RequestBody CreateUserDTO user) {
        return userService.createUser(user);
    }

    //@PutMapping("/edit")
    //public UserModel editPart(@RequestBody CreateUserDTO user) {
      //  return userService.createUser(user);
    //}

    //@GetMapping("/all")
    //public List<UserModel> searchAllOrders() {
      //  return userService.getAllUsers();
    //}
}
