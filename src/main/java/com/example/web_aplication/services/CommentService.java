package com.example.web_aplication.services;

import com.example.web_aplication.dto.CommentDTO;
import com.example.web_aplication.mapper.CommentMapper;
import com.example.web_aplication.model.Comment;
import com.example.web_aplication.model.Post;
import com.example.web_aplication.model.User;
import com.example.web_aplication.repositories.CommentRepository;
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
public class CommentService {
    private static final Logger log = LoggerFactory.getLogger(CommentService.class);
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentMapper commentMapper;


    public Comment addComment(CommentDTO commentDTO) {

        log.info("Создание комментария: {}", commentDTO);
        Comment comment = commentMapper.toEntity(commentDTO);

        Post post = postRepository.findById(commentDTO.getPostId())
                .orElseThrow(() -> new RuntimeException("пост не найден"));
        comment.setPost(post);

        User author = userRepository.findById(commentDTO.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        comment.setAuthor(author);

        Comment savedComment = commentRepository.save(comment);
        log.info("Комментарий успешно создан: {}", savedComment);
        return savedComment;
    }

//    public Comment addComment(Long postId, Comment comment) {
//        log.info("Создание комментария для поста ID: {}", postId);
//
//        Post post = postRepository.findById(postId)
//                .orElseThrow(() -> new RuntimeException("Пост не найден"));
//        comment.setPost(post);
//
//        User author = userRepository.findById(comment.getAuthor().getId())
//                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
//        comment.setAuthor(author);
//
//        Comment savedComment = commentRepository.save(comment);
//        log.info("Комментарий успешно создан: {}", savedComment);
//        return savedComment;
//    }

    public List<Comment> getAllComments() {
        log.info("Получение всех комментарий");
        return commentRepository.findAll();
    }

    public List<Comment> getCommentsByPost(Long postId) {
        log.info("Поиск комментариев для поста с ID {}", postId);
        return commentRepository.findByPostId(postId);
    }
}
