package com.example.web_aplication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PostDTO {
    @NotBlank(message = "Заголовок не может быть пустым")
    private String title;

    @NotBlank(message = "Содержимое не может быть пустым")
    private String content;

    @NotNull(message = "Автор не может быть null")
    private Long authorId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
