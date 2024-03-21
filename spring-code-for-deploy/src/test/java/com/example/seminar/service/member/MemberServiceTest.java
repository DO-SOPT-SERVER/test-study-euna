package com.example.seminar.service.member;

import com.example.seminar.domain.Member;
import com.example.seminar.domain.Part;
import com.example.seminar.domain.SOPT;
import com.example.seminar.dto.response.MemberGetResponse;
import com.example.seminar.fixture.MemberFixture;
import com.example.seminar.fixture.SOPTFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.BDDMockito.given;

@SpringBootTest
public class MemberServiceTest {
    @Mock
    MemberRetriever memberRetriever;

    @InjectMocks
    MemberService memberService;

    private final static Long memberId = 1L;

    @Test
    @DisplayName("id로 Member를 조회할 수 있다")
    void findMemberById() {
        // given
        SOPT sopt = SOPTFixture.createSopt(Part.SERVER);
        MemberGetResponse mockResponse = new MemberGetResponse("성은", "euna", 24, sopt);
        Member expectMember = MemberFixture.createMember("성은","euna",24, sopt);
        given(memberRetriever.findById(memberId)).willReturn(expectMember);

        // when
        MemberGetResponse response = memberService.getMemberById(memberId);

        //then
        Assertions.assertThat(response).isEqualTo(mockResponse);
    }
}
