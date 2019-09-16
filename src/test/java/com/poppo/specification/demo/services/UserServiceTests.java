package com.poppo.specification.demo.services;

import com.poppo.specification.demo.domain.User;
import com.poppo.specification.demo.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class UserServiceTests {

    @Mock
    UserRepository userRepository;

    UserService userService;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        userService = new UserService(userRepository);

    }

    @Test
    public void getUsersTest() {

        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(User.builder().firstName("Poppo").build());

        given(userRepository.findAll()).willReturn(mockUsers);

        User user = mockUsers.get(0);

        assertThat(user.getFirstName()).isEqualTo("Poppo");

    }

    @Test
    public void getUsersWithDtoTest() {

        String search = "firstName:Yeo,lastName:James,email:";

        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(User.builder().firstName("Yeo").build());

        given(userRepository.findAll((Specification<User>) any())).willReturn(mockUsers);

        List<User> users = userService.getUsers(search);
        User user = users.get(0);

        assertThat(user.getFirstName()).isEqualTo("Yeo");

    }

    @Test
    public void getUsersWithSpecificationTest() {

        String search = "firstName:Yeo,lastName:Yeo";

        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(User.builder().firstName("Poppo").build());

        given(userRepository.findAll((Specification<User>) any())).willReturn(mockUsers);

        List<User> users = userService.getUsers(search);
        User user = users.get(0);

        assertThat(user.getFirstName()).isEqualTo("Poppo");

    }

    @Test
    public void addUserTest() {

        User mockUser = User.builder().id(1L).build();

        given(userRepository.save(any())).willReturn(mockUser);

        String firstName = "Cho";
        String lastName = "Poppo";
        String email = "poppo@gmail.com";

        User saved = userService.addUser(firstName, lastName, email);

        assertThat(saved.getId()).isEqualTo(1L);

    }

}
