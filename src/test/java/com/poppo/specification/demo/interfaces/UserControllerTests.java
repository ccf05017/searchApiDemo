package com.poppo.specification.demo.interfaces;

import com.poppo.specification.demo.domain.User;
import com.poppo.specification.demo.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;

    @Test
    public void listWithUserDto() throws Exception {

        String search = "firstName:Poppo,lastName:Cho,email:";

        List<User> users = new ArrayList<>();
        users.add(User.builder().firstName("Cho").lastName("Poppo").build());

        given(userService.getUsers(eq(search))).willReturn(users);

        mvc.perform(get("/users?firstName=Poppo&lastName=Cho"))
                .andExpect(content().string(containsString("[")))
                .andExpect(content().string(containsString("\"firstName\":\"Cho\"")))
        ;

    }

    @Test
    public void listWithSearchParam() throws Exception {

        String search = "firstName:,lastName:,email:";

        List<User> users = new ArrayList<>();
        users.add(User.builder().firstName("Cho").build());

        given(userService.getUsers(search)).willReturn(users);

        mvc.perform(get("/usersSearch?search=" + search))
                .andExpect(content().string(containsString("[")))
                .andExpect(content().string(containsString("\"firstName\":\"Cho\"")))
        ;

        verify(userService).getUsers(eq(search));

    }

    @Test
    public void create() throws Exception {

        User mockSaved = User.builder().id(1L).build();

        given(userService.addUser("Cho", "Poppo", "poppo@gmail.com")).willReturn(mockSaved);

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"firstName\": \"Cho\",\n" +
                        "  \"lastName\": \"Poppo\",\n" +
                        "  \"email\": \"poppo@gmail.com\"\n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(header().stringValues("Location", "/users/1"))
                .andExpect(content().string("{}"));

        verify(userService).addUser("Cho", "Poppo", "poppo@gmail.com");

    }

}
