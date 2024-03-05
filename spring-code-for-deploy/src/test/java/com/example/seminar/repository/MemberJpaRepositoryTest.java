package com.example.seminar.repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class MemberJpaRepositoryTest {
    // 사용자 정보를 입력하면 회원을 등록할 수 있다

    // 사용자 id를 입력하면 회원을 조회할 수 있다

    // 존재하지 않는 사용자 id를 입력하면 회원을 조회할 수 없다



}
