package com.example.web_aplication.services;

import com.example.web_aplication.dto.UserDTO;
import com.example.web_aplication.mapper.UserMapper;
import com.example.web_aplication.model.User;
import com.example.web_aplication.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public User registerUser(UserDTO userDTO){
        log.info("Создание пользователя(юзера): {}", userDTO);
        User user = userMapper.toEntity(userDTO);

        User savedUser = userRepository.save(user);

        log.info("Юзер успешно создан: {}", savedUser);
        return savedUser;
    }

    public User getUserById(Long id) {
        log.info("Поиск пользователя по id");
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь с ID " + id + " не найден"));
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
