package com.poppo.specification.demo.interfaces;

import com.poppo.specification.demo.domain.User;
import com.poppo.specification.demo.dto.UserDto;
import com.poppo.specification.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> list(UserDto userDto) {

        List<User> users = userService.getUsers(
                userDto.toString());

        return users;

    }

    @GetMapping("/usersSearch")
    public List<User> list(
            @RequestParam(value = "search") String search
    ) {

        List<User> users = userService.getUsers(search);

        return users;

    }

    @PostMapping("/users")
    public ResponseEntity create(@RequestBody UserDto userDto) throws URISyntaxException {

        User saved = userService.addUser(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail());

        String url = "/users/" + saved.getId();

        return ResponseEntity.created(new URI(url)).body("{}");

    }

}
