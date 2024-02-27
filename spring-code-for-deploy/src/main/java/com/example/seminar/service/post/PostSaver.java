package com.example.seminar.service.post;

import com.example.seminar.domain.Post;
import com.example.seminar.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostSaver {

    private final PostJpaRepository postJpaRepository;

    public Post save(Post post) {
        return postJpaRepository.save(post);
    }
}
