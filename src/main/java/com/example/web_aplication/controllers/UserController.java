package com.example.web_aplication.controllers;

import com.example.web_aplication.dto.UserDTO;
import com.example.web_aplication.model.User;
import com.example.web_aplication.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Создать юзера", description = "Создает нового юзера")
    @ApiResponse(responseCode = "200", description = "юзер успешно создан")
    @PostMapping
    public User registerUser(@Valid @RequestBody UserDTO userDTO){
        return userService.registerUser(userDTO);
    }

    @Operation(summary = "Получить юзера", description = "Получаем юзера по id")
    @ApiResponse(responseCode = "200", description = "Юзер с таким id успешно найден")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
}
