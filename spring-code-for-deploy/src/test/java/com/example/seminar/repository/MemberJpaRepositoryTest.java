package com.example.seminar.repository;

import com.example.seminar.common.exception.MemberException;
import com.example.seminar.domain.Member;
import com.example.seminar.domain.Part;
import com.example.seminar.domain.SOPT;
import com.example.seminar.fixture.MemberFixture;
import com.example.seminar.fixture.SOPTFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.assertj.core.api.Assertions;

@DataJpaTest
@ActiveProfiles("test")
class MemberJpaRepositoryTest {
    private static final SOPT sopt = SOPTFixture.createSopt(Part.SERVER);
    private static final Member member = MemberFixture.createMember("성은", "euna", 24, sopt);
    private static final Long INVALID_ID = -1L;

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Test
    @DisplayName("사용자 정보를 입력하면 회원을 등록할 수 있다")
    void saveMember(){
        // then
        Member savedMember = memberJpaRepository.save(member);

        // when
        Assertions.assertThat(savedMember)
                .extracting("name", "nickname", "age")
                .containsExactly(member.getName(), member.getNickname(), member.getAge());
    }


    @Test
    @DisplayName("사용자 id를 입력하면 회원을 조회할 수 있다")
    void findMemberById(){
        // given
        Member savedMember = memberJpaRepository.save(member);

        // when
        Member foundMember = memberJpaRepository.findByIdOrThrow(savedMember.getId());

        // then
        Assertions.assertThat(foundMember)
                .extracting("name", "nickname", "age")
                .containsExactly(savedMember.getName(), savedMember.getNickname(), savedMember.getAge());
    }

    @Test
    @DisplayName("존재하지 않는 사용자 id를 입력하면 회원을 조회할 수 없다")
    void findMemberByInvalidId(){
        Member savedMember = memberJpaRepository.save(member);

        Assertions.assertThatThrownBy(
                () -> {
                    memberJpaRepository.findByIdOrThrow(INVALID_ID);
                }
        ).isInstanceOf(MemberException.class)
                .hasMessage("존재하지 않는 회원입니다.");
    }
}
