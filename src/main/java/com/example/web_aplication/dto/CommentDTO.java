package com.example.web_aplication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CommentDTO {
    @NotBlank(message = "Текст комментария не может быть пустым")
    private String text;

    @NotNull(message = "Пост не может быть null")
    private Long postId;

    @NotNull(message = "Автор не может быть null")
    private Long authorId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
