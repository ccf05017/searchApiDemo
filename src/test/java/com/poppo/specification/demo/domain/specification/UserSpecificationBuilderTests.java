package com.poppo.specification.demo.domain.specification;


import org.junit.Before;
import org.junit.Test;
import org.springframework.data.jpa.domain.Specification;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class UserSpecificationBuilderTests {

    private UserSpecificationBuilder userSpecificationBuilder;

    @Before
    public void setup() {

        userSpecificationBuilder = new UserSpecificationBuilder();

    }

    @Test
    public void testWith() {

        // with(String key, String operation, Object value): return UserSpecificationBuilder
        // UserSpecificationBuilder params 안에 SearchCiteria를 넣어줌.

        String key = "testKey";
        String operation = "testOperation";
        String value = "testValue";

        String key2 = "testKey2";
        String operation2 = "testOperation2";
        String value2 = null;

        UserSpecificationBuilder builderResult = userSpecificationBuilder.with(key, operation, value);
        builderResult = userSpecificationBuilder.with(key2, operation2, value2);

        assertThat(builderResult.getParams().get(0).getKey()).isEqualTo("testKey");
        assertThat(builderResult.getParams().get(0).getOperation()).isEqualTo("testOperation");
        assertThat(builderResult.getParams().get(0).getValue()).isEqualTo("testValue");

        assertThat(builderResult.getParams()).extracting("key").contains("testKey");
        assertThat(builderResult.getParams()).extracting("key").doesNotContain("testKey2");

    }

    // TODO
    // Specification<User> 테스트 먼저 진행하고 다시 진행
    @Test
    public void testBuild() {

        String key = "testKey";
        String operation = "testOperation";
        String value = "testValue";

        userSpecificationBuilder.with(key, operation, value);
        Specification resultSpecification = userSpecificationBuilder.build();

        // 여길 어떻게 테스트해야 되지..?
        assertThat(resultSpecification.toString()).isIn("hello");

    }

}
