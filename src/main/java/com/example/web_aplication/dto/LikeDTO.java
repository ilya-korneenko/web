package com.example.web_aplication.dto;

import com.example.web_aplication.model.Post;
import com.example.web_aplication.model.User;
import jakarta.validation.constraints.NotNull;

public class LikeDTO {
    @NotNull(message = "id пользователя не может быть Null")
    private Long userId;

    @NotNull(message = "id поста не может быть Null")
    private Long postId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
