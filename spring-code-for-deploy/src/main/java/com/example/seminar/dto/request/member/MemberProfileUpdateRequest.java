package com.example.seminar.dto.request.member;

import com.example.seminar.domain.Part;

public record MemberProfileUpdateRequest (
        short generation,
        Part part
){
}