package com.zhuravishkin.springbootoracledbplsqlprocedure.controller;

import com.zhuravishkin.springbootoracledbplsqlprocedure.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Controller controller;

    @MockBean
    private UserService service;

    @Test
    void getEmail() throws Exception {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .pathSegment("user/email/79306661000")
                .build()
                .encode();
        mockMvc.perform(get(uriComponents.toUri()))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"))
                .andReturn();
    }

    @Test
    void getEmailException() throws Exception {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .pathSegment("user/email/79306661000a")
                .build()
                .encode();
        mockMvc.perform(get(uriComponents.toUri()))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }
}