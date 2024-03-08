package com.example.seminar.fixture;

import com.example.seminar.domain.Member;
import com.example.seminar.domain.Post;

public abstract class PostFixture {
    public static Post createPost(String title,
                                  String content,
                                  Member member){
        return Post.builder()
                .title(title)
                .content(content)
                .member(member)
                .build();
    }
}
