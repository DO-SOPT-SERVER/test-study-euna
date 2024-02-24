package com.example.seminar.dto.response.post;

import com.example.seminar.domain.Category;
import com.example.seminar.domain.Post;

public record PostGetResponse(
        Long id,
        String title,
        String content,
        String category
) {
    public static PostGetResponse of(Post post, Category category) {
        return new PostGetResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                category.getContent()
        );
    }
}
