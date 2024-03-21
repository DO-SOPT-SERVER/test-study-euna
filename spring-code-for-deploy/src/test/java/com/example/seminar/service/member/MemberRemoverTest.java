package com.example.seminar.service.member;

import com.example.seminar.common.exception.MemberException;
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
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
public class MemberRemoverTest {

    @Autowired
    MemberRemover memberRemover;

    @Autowired
    MemberRegister memberRegister;

    @Autowired
    MemberRetriever memberRetriever;

    private static final SOPT soptFixture = SOPTFixture.createSopt(Part.SERVER);
    private static final Member memberFixture = MemberFixture.createMember("성은", "euna", 24, soptFixture);

    @Test
    @DisplayName("회원을 삭제할 경우, 회원을 조회할 수 없다")
    @Transactional
    void remove() {
        // given
        Member savedMember = memberRegister.register(memberFixture);
        Long savedMemberId = savedMember.getId();

        // when
        memberRemover.remove(savedMember);

        //then
        Assertions.assertThatThrownBy(
                        () -> {
                            memberRetriever.findById(savedMemberId);
                        }
                ).isInstanceOf(MemberException.class)
                .hasMessage("해당하는 회원이 없습니다.");
    }
}
