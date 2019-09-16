package com.poppo.specification.demo.domain.specification;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@Transactional
public class UserSpecificationTests {

    UserSpecification userSpecification;

    @Before
    public void setup() {

        SearchCriteria searchCriteria = new SearchCriteria("firstName", ":", "Yeo");

        userSpecification = new UserSpecification(searchCriteria);

    }

    @Test
    public void specificationTest() {

        // 이건 또 어떻게 테스트 하지...?
        // userSpecification.toPredicate();

    }

}
