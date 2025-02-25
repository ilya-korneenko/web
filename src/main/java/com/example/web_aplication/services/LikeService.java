package com.example.web_aplication.services;

import com.example.web_aplication.dto.LikeDTO;
import com.example.web_aplication.mapper.LikeMapper;
import com.example.web_aplication.model.Like;
import com.example.web_aplication.model.Post;
import com.example.web_aplication.model.User;
import com.example.web_aplication.repositories.LikeRepository;
import com.example.web_aplication.repositories.PostRepository;
import com.example.web_aplication.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LikeService {
    private static final Logger log = LoggerFactory.getLogger(LikeService.class);
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikeMapper likeMapper;

    public Like addLike(LikeDTO likeDTO){
        log.info("Создание лайка: {}", likeDTO);
        Like like = likeMapper.toEntity(likeDTO);

        Post post = postRepository.findById(likeDTO.getPostId())
                        .orElseThrow(() -> new RuntimeException("Пост с ID " + likeDTO.getPostId() +
                                " не найден"));
        like.setPost(post);

        User user = userRepository.findById(likeDTO.getPostId())
                        .orElseThrow(() -> new RuntimeException("Пользователь с ID " + likeDTO.getPostId() +
                                " не найден"));
        like.setUser(user);

        Like savedLike = likeRepository.save(like);
        log.info("Лайк успешно создан: {}", savedLike);

        return savedLike;
    }

    public int getLikeCount(Post post){
        log.info("Получение количества лайков под постом");
        return likeRepository.countByPost(post);
    }
}
