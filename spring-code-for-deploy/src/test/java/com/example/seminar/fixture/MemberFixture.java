package com.example.seminar.fixture;

import com.example.seminar.domain.Member;
import com.example.seminar.domain.SOPT;

public abstract class MemberFixture {
    public static Member createMember(String name,
                                      String nickname,
                                      int age,
                                      SOPT sopt){
        return Member.builder()
                .name(name)
                .nickname(nickname)
                .age(age)
                .sopt(sopt)
                .build();
    }

}
