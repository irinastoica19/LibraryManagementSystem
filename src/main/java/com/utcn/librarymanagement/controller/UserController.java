package com.utcn.librarymanagement.controller;

import com.utcn.librarymanagement.dto.LoginDTO;
import com.utcn.librarymanagement.dto.UserAddDTO;
import com.utcn.librarymanagement.dto.UserDTO;
import com.utcn.librarymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserDTO login(@RequestBody LoginDTO dto){
        return userService.login(dto.getEmail(), dto.getPassword());
    }

    @PostMapping()
    public UserDTO insertUser(@RequestBody UserAddDTO userDTO){
        return userService.insertUser(userDTO);
    }
}
