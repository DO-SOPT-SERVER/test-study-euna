package com.example.seminar.domain;


import com.example.seminar.common.exception.PostException;
import com.example.seminar.fixture.MemberFixture;
import com.example.seminar.fixture.PostFixture;
import com.example.seminar.fixture.SOPTFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class PostTest {
    private static final SOPT sopt = SOPTFixture.createSopt(Part.SERVER);
    private static final Member member = MemberFixture.createMember("성은", "euna", 24, sopt);

    @Test
    @DisplayName("게시글의 제목이 100자를 넘을 경우 예외가 발생한다")
    void InvalidTitleExceed100(){
        // given
        String title = "a".repeat(101);
        String content = "content";
        Assertions.assertThatThrownBy(
                () -> {
                    Post post = PostFixture.createPost(title, content, member);
                }
        ).isInstanceOf(PostException.class)
        .hasMessage("제목은 100자를 넘을 수 없습니다.");
    }
}
