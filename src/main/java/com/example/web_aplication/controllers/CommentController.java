package com.example.web_aplication.controllers;


import com.example.web_aplication.dto.CommentDTO;
import com.example.web_aplication.model.Comment;
import com.example.web_aplication.services.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts") // 👈 Теперь все маршруты начинаются с /posts
public class CommentController {
    private static final Logger log = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @Operation(summary = "Создание комментария", description = "Создает новый комментарий")
    @ApiResponse(responseCode = "200", description = "Комментарий успешно создан")
    @PostMapping("/{postId}/comments") // 👈 Создаем комментарий для конкретного поста
    public ResponseEntity<Comment> addComment(@PathVariable Long postId, @Valid @RequestBody CommentDTO commentDTO) {
        log.info("Создание комментария для поста ID {}", postId);

        commentDTO.setPostId(postId);
        Comment createdComment = commentService.addComment(commentDTO);
        return ResponseEntity.ok(createdComment);
    }

    @Operation(summary = "Получить все комментарии", description = "Возвращает список всех комментариев")
    @ApiResponse(responseCode = "200", description = "Список комментариев успешно получен")
    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAllComments() {
        log.info("Получение всех комментариев");
        List<Comment> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    @Operation(summary = "Получить комментарии к посту", description = "Возвращает список комментариев по postId")
    @ApiResponse(responseCode = "200", description = "Комментарии успешно получены")
    @GetMapping("/{postId}/comments") // 👈 Исправленный маршрут
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable Long postId) {
        log.info("Получение комментариев для поста с ID {}", postId);
        List<Comment> comments = commentService.getCommentsByPost(postId);
        return ResponseEntity.ok(comments);
    }
}
