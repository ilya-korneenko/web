package com.example.web_aplication.services;

import com.example.web_aplication.dto.PostDTO;
import com.example.web_aplication.mapper.PostMapper;
import com.example.web_aplication.model.Post;
import com.example.web_aplication.model.User;
import com.example.web_aplication.repositories.PostRepository;
import com.example.web_aplication.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PostService {
    private static final Logger log = LoggerFactory.getLogger(PostService.class);
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostMapper postMapper;

    public Post createPost(PostDTO postDTO) {
        log.info("Создание поста: {}", postDTO);
        Post post = postMapper.toEntity(postDTO);

        User author = userRepository.findById(postDTO.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        post.setAuthor(author);
        Post savedPost = postRepository.save(post);
        log.info("Пост успешно создан: {}", savedPost);
        return savedPost;
    }

    public List<Post> getAllPosts() {
        log.info("Получение всех постов");
        return postRepository.findAll();
    }

//    public Post getPostByAuthor(String author){
//        return postRepository.findByAuthor(author);
//    }
}
