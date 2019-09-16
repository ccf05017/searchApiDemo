package com.poppo.specification.demo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String firstName;
    private String lastName;
    private String email;

    public static String isNull(String target) {

        if (target == null) {
            return "";
        }

        return target;
    }

    @Override
    public String toString() {
        return  "firstName:" + isNull(firstName) +
                ",lastName:" + isNull(lastName) +
                ",email:" + isNull(email);
    }
}
