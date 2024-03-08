package com.example.seminar.domain;


import com.example.seminar.common.exception.MemberException;
import com.example.seminar.fixture.MemberFixture;
import com.example.seminar.fixture.SOPTFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class MemberTest {

    @Test
    @DisplayName("회원의 나이는 0살 미만일 경우 예외가 발생한다")
    void invalidAgeUnderZero(){
        Assertions.assertThatThrownBy(
                        () -> {
                            SOPT sopt = SOPTFixture.createSopt(Part.SERVER);
                            Member member = MemberFixture.createMember("성은","euna", -1, sopt);
                        }
                ).isInstanceOf(MemberException.class)
                .hasMessage("회원의 나이는 0세 이상 100세 이하입니다.");
    }

    @Test
    @DisplayName("회원의 나이가 100살을 넘을 경우 예외가 발생한다")
    void invalidAgeExceed100(){
        Assertions.assertThatThrownBy(
                        () -> {
                            SOPT sopt = SOPTFixture.createSopt(Part.SERVER);
                            Member member = MemberFixture.createMember("성은","euna", 101, sopt);
                        }
                ).isInstanceOf(MemberException.class)
                .hasMessage("회원의 나이는 0세 이상 100세 이하입니다.");
    }

    @Test
    @DisplayName("회원의 이름이 한글이 아닐 경우 예외가 발생한다")
    void invalidName(){
        Assertions.assertThatThrownBy(
                        () -> {
                            SOPT sopt = SOPTFixture.createSopt(Part.SERVER);
                            Member member = MemberFixture.createMember("tjddms","euna", 24, sopt);
                        }
                ).isInstanceOf(MemberException.class)
                .hasMessage("유저의 이름은 한글만 가능합니다.");
    }

    @Test
    @DisplayName("회원의 닉네임이 12자를 초과하면 예외가 발생한다")
    void invalidNickname(){
        Assertions.assertThatThrownBy(
                () -> {
                    SOPT sopt = SOPTFixture.createSopt(Part.SERVER);
                    Member member = MemberFixture.createMember("성은","eunaeunaeunaeuna", 24, sopt);
                }
        ).isInstanceOf(MemberException.class)
        .hasMessage("유저의 닉네임은 12자를 넘을 수 없습니다.");
    }
}
