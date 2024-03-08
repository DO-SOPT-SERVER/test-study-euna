package com.example.seminar.domain;


import com.example.seminar.common.exception.PostException;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Post(String title, String content, Member member) {
        validateTitle(title);
        this.title = title;
        this.content = content;
        this.member = member;
    }

    private void validateTitle(String title) {
        if(title.length() > 100) {
            throw new PostException("제목은 100자를 넘을 수 없습니다.");
        }
    }

    public void updateContent(final String content) {
        this.content = content;
    }
}
