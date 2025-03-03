package com.example.web_aplication.controllers;

import com.example.web_aplication.dto.PostDTO;
import com.example.web_aplication.model.Post;
import com.example.web_aplication.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Operation(summary = "создание поста", description = "Создает новый пост")
    @ApiResponse(responseCode = "200", description = "Пост успешно создан")
    @PostMapping
    public Post createPost(@Valid @RequestBody PostDTO postDTO) {
        return postService.createPost(postDTO);
    }

    @Operation(summary = "Получить все посты", description = "Возвращает список всех постов")
    @ApiResponse(responseCode = "200", description = "Список постов успешно создан")
    @GetMapping
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @Operation(summary = "Редактирование поста", description = "редактирует существующий пост")
    @ApiResponse(responseCode = "200", description = "Пост успешно обнолен")
    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @Valid @RequestBody PostDTO postDTO){
        return postService.updatePost(id, postDTO);
    }

    @Operation(summary = "Удаление поста", description = "Удаляет пост по ID")
    @ApiResponse(responseCode = "200", description = "Пост успешно удален")
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    @Operation(summary = "Получение поста по ID", description = "Возвращает пост по id")
    @ApiResponse(responseCode = "200", description = "Пост успешно найден")
    @GetMapping("/{id}")
    public void getPostById(@PathVariable Long id) {
        postService.getPostById(id);
    }
}
