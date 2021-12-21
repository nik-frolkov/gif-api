package com.nikfrolkov.gif.api.controller;

import com.nikfrolkov.gif.api.service.GifService;
import com.nikfrolkov.gif.api.AbstractGifTest;
import com.nikfrolkov.gif.api.controller.dto.DataDto;
import com.nikfrolkov.gif.api.controller.dto.GifResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class GifControllerTest extends AbstractGifTest {

    @MockBean
    private GifService gifService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getGifByCurrency() throws Exception {

        DataDto dataDto = new DataDto("gif", "some_title", "url");
        GifResponse gifResponse = new GifResponse(dataDto, "some_tag");

        when(gifService.getGif("RUB")).thenReturn(gifResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/gif/RUB"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.tag", Matchers.equalTo("some_tag")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.type", Matchers.equalTo("gif")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.title", Matchers.equalTo("some_title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.url", Matchers.equalTo("url")));
    }
}