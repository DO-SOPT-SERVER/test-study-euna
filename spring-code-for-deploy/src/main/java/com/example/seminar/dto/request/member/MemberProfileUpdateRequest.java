package com.example.seminar.dto.request.member;

import com.example.seminar.domain.Part;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberProfileUpdateRequest {
    private short generation;
    private Part part;
}

