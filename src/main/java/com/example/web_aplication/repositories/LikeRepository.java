package com.example.web_aplication.repositories;

import com.example.web_aplication.model.Like;
import com.example.web_aplication.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    int countByPost(Post post);
}
