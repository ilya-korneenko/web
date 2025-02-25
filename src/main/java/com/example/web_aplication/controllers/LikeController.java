package com.example.web_aplication.controllers;

import com.example.web_aplication.dto.LikeDTO;
import com.example.web_aplication.model.Like;
import com.example.web_aplication.services.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @Operation(summary = "создание лайка", description = "Создает новый лайк")
    @ApiResponse(responseCode = "200", description = "Лайк успешно создан")
    @PostMapping
    public Like addLike(@RequestBody LikeDTO likeDTO){
        return likeService.addLike(likeDTO);
    }
}
