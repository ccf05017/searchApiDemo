package com.poppo.specification.demo.dto;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDtoTests {

    @Test
    public void toStringNoDataTest() {

        UserDto userDto = UserDto.builder().build();

        assertThat(userDto.toString()).isEqualTo("firstName:,lastName:,email:");

    }

    @Test
    public void toStringDataTest() {

        UserDto userDto = UserDto.builder().firstName("Yeo").build();

        assertThat(userDto.toString()).isEqualTo("firstName:Yeo,lastName:,email:");

    }

    @Test
    public void isNullNotNullTest() {

        String firstName = "test";

        String result = UserDto.isNull(firstName);

        assertThat(result).isEqualTo("test");

    }

    @Test
    public void isNullNullTest() {

        String firstName = null;

        String result = UserDto.isNull(firstName);

        assertThat(result).isEqualTo("");

    }

}
