package com.example.seminar.service.member;

import com.example.seminar.domain.Member;
import com.example.seminar.domain.Part;
import com.example.seminar.domain.SOPT;
import com.example.seminar.fixture.MemberFixture;
import com.example.seminar.fixture.SOPTFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class MemberRegisterTest {

    @Autowired
    private MemberRegister memberRegister;

    private static final SOPT soptFixture = SOPTFixture.createSopt(Part.SERVER);
    private static final Member memberFixture = MemberFixture.createMember("성은", "euna", 24, soptFixture);

    @Test
    @DisplayName("SOPT 회원을 등록할 수 있다.")
    void register() {
        // given, when
        Member savedMember = memberRegister.register(memberFixture);

        // then
        Assertions.assertThat(memberFixture.getName()).isEqualTo(savedMember.getName());
    }

    @Test
    @DisplayName("새롭게 등록한 SOPT 회원은 현재 기수이다.")
    void checkIsCurrentGeneration() {
        // given, when
        Member savedMember = memberRegister.register(memberFixture);

        //then
        Assertions.assertThat(savedMember.getSopt().getGeneration()).isEqualTo(SOPTFixture.CURRENT_GENERATION);
    }
}
