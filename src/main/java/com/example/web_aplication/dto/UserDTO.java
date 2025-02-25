package com.example.web_aplication.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDTO {

    @NotBlank(message = "email не может быть пустым")
    private String email;

    @NotBlank(message = "пароль не может быть пустым")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
