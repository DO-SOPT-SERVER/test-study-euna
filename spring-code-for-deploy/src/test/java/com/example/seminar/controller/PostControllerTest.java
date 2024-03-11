package com.example.seminar.controller;

import com.example.seminar.dto.request.post.PostCreateRequest;
import com.example.seminar.dto.request.post.PostUpdateRequest;
import com.example.seminar.service.post.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = PostController.class)
class PostControllerTest extends ControllerTestManager{
    @MockBean
    private PostService postService;

    private String CUSTOM_USER_ID = "X-Auth-Id";
    private static final String POST_API_ENDPOINT = "/api/posts";

    @Test
    @DisplayName("게시글을 등록한다")
    void createPost() throws Exception {
        // given
        when(postService.create(any(PostCreateRequest.class), any(Long.class)))
                .thenReturn("1");

        PostCreateRequest request = new PostCreateRequest("안녕하세요", "잘부탁드립니다");

        // when then
        mockMvc.perform(
                        MockMvcRequestBuilders.post(POST_API_ENDPOINT)
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(json)
                                .header(CUSTOM_USER_ID, 1L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", "/api/posts/1"));
    }



    @Test
    void updatePost() throws Exception {
        // given
        PostUpdateRequest request = new PostUpdateRequest("종강주세요");

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.patch(POST_API_ENDPOINT+"/1")
                .contentType(json)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void deletePost() throws Exception {
        // given, when, then
        mockMvc.perform(MockMvcRequestBuilders.delete(POST_API_ENDPOINT+"/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}