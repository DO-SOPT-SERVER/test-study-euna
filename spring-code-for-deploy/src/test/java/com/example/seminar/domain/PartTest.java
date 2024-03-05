package com.example.seminar.domain;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
class PartTest {

    @Test
    @DisplayName("개발 파트만을 조회할 수 있다.")
    void developParts() {
        Assertions.assertThat(Part.developParts())
                .isEqualTo(
                        List.of(
                                Part.SERVER,
                                Part.WEB,
                                Part.ANDROID,
                                Part.IOS
                        ));
    }

    @Test
    @DisplayName("클라이언트 파트만을 조회할 수 있다")
    void clientDevelopParts() {
        Assertions.assertThat(Part.clientDevelopParts())
                .isEqualTo(
                        List.of(
                                Part.WEB,
                                Part.ANDROID,
                                Part.IOS
                        ));
    }
}
