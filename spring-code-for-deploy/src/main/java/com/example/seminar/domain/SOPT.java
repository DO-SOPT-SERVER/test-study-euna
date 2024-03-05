package com.example.seminar.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.*;

import static jakarta.persistence.EnumType.*;
import static lombok.AccessLevel.PROTECTED;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
// DDD의 Value Object
public class SOPT {

    private static final short CURRENT_GENERATION = 34;

    private short generation;

    @Enumerated(STRING)
    private Part part;

    @Builder
    private SOPT(Part part) {
        this.generation = CURRENT_GENERATION;
        this.part = part;
    }
}
