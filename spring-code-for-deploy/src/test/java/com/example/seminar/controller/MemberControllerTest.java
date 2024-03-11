package com.example.seminar.controller;

import com.example.seminar.domain.Part;
import com.example.seminar.domain.SOPT;
import com.example.seminar.dto.request.member.MemberCreateRequest;
import com.example.seminar.dto.request.member.MemberProfileUpdateRequest;
import com.example.seminar.dto.response.MemberGetResponse;
import com.example.seminar.fixture.MemberFixture;
import com.example.seminar.fixture.SOPTFixture;
import com.example.seminar.service.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = MemberController.class)
class MemberControllerTest extends ControllerTestManager {

    @MockBean
    private MemberService memberservice;

    @Test
    @DisplayName("신규 회원을 등록한다")
    void createMember() throws Exception {
        // given
        when(memberservice.create(any(MemberCreateRequest.class)))
                .thenReturn("/api/member/1");

        MemberCreateRequest request = new MemberCreateRequest
                ("성은",
                 "euna",
                 24, SOPT.builder().part(Part.SERVER).build());

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/member")
                .content(objectMapper.writeValueAsString(request))
                .contentType(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("회원 정보 목록을 조회한다")
    void getMembersProfile() throws Exception {
        // given
        SOPT sopt = SOPTFixture.createSopt(Part.SERVER);
        List<MemberGetResponse> memberResponses = Stream.of(
                        MemberFixture.createMember("성은", "euna", 24, sopt),
                        MemberFixture.createMember("정원", "garden", 24, sopt)
                )
                .map(MemberGetResponse::of) // 레코드의 of 메서드를 이용하여 매핑
                .toList();
        when(memberservice.getMembers())
                .thenReturn(memberResponses);

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/member"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].name").value("성은"))
                .andExpect(jsonPath("$[0].nickname").value("euna"))
                .andExpect(jsonPath("$[0].age").value(24))
                .andExpect(jsonPath("$[0].soptInfo.generation").value(34))
                .andExpect(jsonPath("$[0].soptInfo.part").value("SERVER"))
                .andExpect(jsonPath("$[1].name").value("정원"))
                .andExpect(jsonPath("$[1].nickname").value("garden"))
                .andExpect(jsonPath("$[1].age").value(24))
                .andExpect(jsonPath("$[0].soptInfo.generation").value(34))
                .andExpect(jsonPath("$[0].soptInfo.part").value("SERVER"));
    }

    @Test
    @DisplayName("회원 정보를 수정한다.")
    void updateMemberSoptInfo() throws Exception {
        // given
        MemberProfileUpdateRequest request = new MemberProfileUpdateRequest((short) 6, Part.SERVER);

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/member/1")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @DisplayName("회원 정보를 삭제한다.")
    void deleteMember() throws Exception {
        // given // when // then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/member/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

}