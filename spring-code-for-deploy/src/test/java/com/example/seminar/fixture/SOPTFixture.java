package com.example.seminar.fixture;

import com.example.seminar.domain.Part;
import com.example.seminar.domain.SOPT;

public abstract class SOPTFixture {
    public static final short CURRENT_GENERATION = 34;

    public static SOPT createSopt(Part part){
        return SOPT.builder()
                .part(part)
                .build();
    }
}
