package com.poppo.specification.demo.domain.specification;

import com.poppo.specification.demo.domain.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSpecification implements Specification<User> {

    private SearchCriteria criteria;

    public UserSpecification(SearchCriteria searchCriteria) {

        this.criteria = searchCriteria;

    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        // 대소 비교 하지 않을 거라 필요 없음
//        if (criteria.getOperation().equalsIgnoreCase(">")) {
//            return criteriaBuilder.greaterThanOrEqualTo(
//                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
//        }
//        else if (criteria.getOperation().equalsIgnoreCase("<")) {
//            return criteriaBuilder.lessThanOrEqualTo(
//                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
//        }

        if (root.get(criteria.getKey()).getJavaType() == String.class) {
            return criteriaBuilder.like(
                    root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
        } else {
            return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
        }

    }

}
