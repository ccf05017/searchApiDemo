package com.poppo.specification.demo.domain.specification;

import com.poppo.specification.demo.domain.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserSpecificationBuilder {

    private final List<SearchCriteria> params;

    public UserSpecificationBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public UserSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));

        return this;
    }

    public Specification<User> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(UserSpecification::new)
                .collect(Collectors.toList());

        // 한개일 때는 and, or 할 필요가 엄슴
        Specification result = specs.get(0);

        // 여러개일 때 and인지 or인지 구분하도록 구현
        // 현재 서비스에서는 or 필요 없지 않나? -> 일단 필요 없는 걸로
        for (int i = 1; i < params.size(); i++) {
//            result = params.get(i).isOrPredicate()
//                    ? Specification.where(result).or(specs.get(i))
//                    : Specification.where(result).and(specs.get(i));

            // 전부 and 절로 연결
            result = Specification.where(result).and(specs.get(i));
        }

        return result;
    }

    // for test
    public List<SearchCriteria> getParams() {
        return params;
    }
}
