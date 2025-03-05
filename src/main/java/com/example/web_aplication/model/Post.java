package com.example.web_aplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

//@Data             //позже все можно реализовать через lombook, но пока остается так
//@Getter
//@Setter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Заголовок не может быть пустым")
    @Size(max = 255, message = "Заголовок не может превышать 255 символов")
    private String title;

    @NotBlank(message = "Содержимое не может быть пустым")
    @Size(max = 2000, message = "Содержимое не может превышать 2000 символов")
    private String content;

    @NotNull(message = "Автор не может быть null")
    @ManyToOne
    @JoinColumn(name = "author_id")         //это было добавлено
    private User author;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }





}
