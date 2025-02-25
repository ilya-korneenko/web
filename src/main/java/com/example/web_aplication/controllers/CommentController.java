package com.example.web_aplication.controllers;

import com.example.web_aplication.dto.CommentDTO;
import com.example.web_aplication.model.Comment;
import com.example.web_aplication.services.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Operation(summary = "создание комментария", description = "Создает новый комментарий")
    @ApiResponse(responseCode = "200", description = "Комментарий успешно создан")
    @PostMapping
    public Comment addComment(@Valid @RequestBody CommentDTO commentDTO) {
        return commentService.addComment(commentDTO);
    }

    @GetMapping
    public List<Comment> getAllComments(){
        return commentService.getAllComments();
    }
}
