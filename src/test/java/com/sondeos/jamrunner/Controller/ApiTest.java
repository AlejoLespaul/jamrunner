package com.sondeos.jamrunner.Controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(Api.class)
public class ApiTest {
    @Autowired
    MockMvc mvc;

    @Test
    public void testEndpointWithValidRepo() throws Exception {
        String repo = "https://github.com/AlejoLespaul/factorialize";
        String name = "GroupA";

        MockHttpServletResponse response = mvc.perform(post("/api/js")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{" +
                        "\"repo\": \"" + repo + "\"," +
                        "\"name\": \"" + name + "\"" +
                        "}"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        assertTrue(response.getContentAsString().contains("testPassed"));
        assertTrue(response.getContentAsString().contains("testFailed"));
        assertTrue(response.getContentAsString().contains("performance"));
        assertTrue(response.getContentAsString().contains("lines"));
    }

}
