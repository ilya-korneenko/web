package com.example.web_aplication.repositories;

import com.example.web_aplication.model.Post;
import com.example.web_aplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(User author);   // Поиск постов по автору
}
