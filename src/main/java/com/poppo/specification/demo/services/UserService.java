package com.poppo.specification.demo.services;

import com.poppo.specification.demo.domain.User;
import com.poppo.specification.demo.domain.UserRepository;
import com.poppo.specification.demo.domain.specification.UserSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;

    }

    public List<User> getUsers(String search) {

        Specification<User> spec = this.userSpecification(search);

        return userRepository.findAll(spec);

    }

    public User addUser(String firstName, String lastName, String email) {

        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();

        User saved = userRepository.save(user);

        return saved;

    }

    // 인터페이스는 어떻게 테스트해야 되나..?
    protected Specification<User> userSpecification(String search) {

        UserSpecificationBuilder userSpecificationBuilder = new UserSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            userSpecificationBuilder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        return userSpecificationBuilder.build();

    }

}
