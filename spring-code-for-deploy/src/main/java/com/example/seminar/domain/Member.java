package com.example.seminar.domain;


import com.example.seminar.common.exception.MemberException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    private static final int MAX_AGE = 100;
    private static final int MAX_LENGTH = 12;
    private static final short CURRENT_GENERATION = 34;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String nickname;
    private int age;

    private boolean isDeleted = false;

    @Embedded
    private SOPT sopt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private final List<Post> posts = new ArrayList<>();

    @Builder
    private Member(String name,
                   String nickname,
                   int age,
                   SOPT sopt) {
        validateAge(age);
        validateName(name);
        validateNickname(nickname);
        this.name = name;
        this.nickname = nickname;
        this.age = age;
        this.sopt = sopt;
        this.isDeleted = false;
    }

    private void validateAge(final int age) {
        if (0 > age || age > MAX_AGE) {
            throw new MemberException("회원의 나이는 0세 이상 100세 이하입니다.");
        }
    }

    private void validateName(final String name) {
       if (!name.matches("^[가-힣]*$")) {
            throw new MemberException("유저의 이름은 한글만 가능합니다.");
       }
    }

    private void validateNickname(final String nickname){
        if (nickname.length() > MAX_LENGTH) {
            throw new MemberException("유저의 닉네임은 12자를 넘을 수 없습니다.");
        }
    }

    public void remove(){
        this.isDeleted = true;
    }

    public void updateSOPT(SOPT sopt) {
        this.sopt = sopt;
    }
}
