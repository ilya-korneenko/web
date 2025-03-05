package com.example.web_aplication.controllers;

import com.example.web_aplication.dto.PostDTO;
import com.example.web_aplication.model.Post;
import com.example.web_aplication.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/posts")
public class PostController {

    private static final Logger log = LoggerFactory.getLogger(PostController.class);

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
    public ResponseEntity<?> updatePost(@PathVariable Long id, @Valid @RequestBody PostDTO postDTO){
        log.info("Запрос на обновление поста ID: {}", id);
        log.info("Данные для обновления: {}", postDTO);
        log.info(">> Контроллер получил запрос на обновление поста с ID {}", id);
        try {
            Post updatedPost = postService.updatePost(id, postDTO);
            log.info(">> Контроллер отправляет обновленный пост клиенту {}", updatedPost);
            return ResponseEntity.ok(updatedPost);  // ✅ Гарантированно возвращаем JSON
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
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
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }
}
