package com.bmd.connecty.connectstart.controller;

import com.bmd.connecty.connectstart.configuration.GameConfiguration;
import com.bmd.connecty.connectstart.game.Game;
import com.bmd.connecty.connectstart.game.GameImpl;
import com.bmd.connecty.connectstart.handlers.RestExceptionHandler;
import com.bmd.connecty.connectstart.service.UserService;
import com.bmd.connecty.connectstart.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.util.StreamUtils.copyToString;

import static java.nio.charset.Charset.defaultCharset;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        "connect4.height=6",
        "connect4.width=9",
},
        classes = {
        UserController.class, RestExceptionHandler.class, UserServiceImpl.class, GameConfiguration.class
})
@AutoConfigureWebMvc
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    UserService userService;

    @Mock
    Game game;



    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    public void test_one_user() throws Exception {

        String body = copyToString(
                UserControllerTest.class.getClassLoader().getResourceAsStream("__files/user_ted.json"),
                defaultCharset());

        MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders.post("/v1/connect4/user/")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(body))
                        .andReturn().getResponse();
        assertNotNull(response);
        assertEquals((HttpStatus.OK.value()), response.getStatus());
        System.out.println("tttt\n"+response.getContentAsString());

        String expected = copyToString(
                UserControllerTest.class.getClassLoader().getResourceAsStream("__files/user_ted_response.json"),
                defaultCharset());
        assertEquals(expected, response.getContentAsString());

    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    public void test_two_users() throws Exception {

        String body = copyToString(
                UserControllerTest.class.getClassLoader().getResourceAsStream("__files/user_ted.json"),
                defaultCharset());

        MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders.post("/v1/connect4/user/")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(body))
                        .andReturn().getResponse();
        assertNotNull(response);
        assertEquals((HttpStatus.OK.value()), response.getStatus());

        body = copyToString(
                UserControllerTest.class.getClassLoader().getResourceAsStream("__files/user_sid.json"),
                defaultCharset());

        response =
                mockMvc.perform(MockMvcRequestBuilders.post("/v1/connect4/user/")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(body))
                        .andReturn().getResponse();
        assertNotNull(response);
        assertEquals((HttpStatus.OK.value()), response.getStatus());
        System.out.println("tttt\n"+response.getContentAsString());

        String expected = copyToString(
                UserControllerTest.class.getClassLoader().getResourceAsStream("__files/user_sid_response.json"),
                defaultCharset());
        assertEquals(expected, response.getContentAsString());

    }

}
