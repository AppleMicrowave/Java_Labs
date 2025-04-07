package com.example.demo;

import jakarta.persistence.criteria.Join;
import com.example.demo.model.Order;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class OrderSpecification {

    public static Specification<Order> build(OrderSearchCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            Join<Object, Object> customerJoin = root.join("customer", JoinType.LEFT);
            Join<Object, Object> addressJoin = customerJoin.join("address", JoinType.LEFT);

            if (criteria.getCity() != null) {
                predicates.add(cb.equal(addressJoin.get("city"), criteria.getCity()));
            }
            if (criteria.getStreet() != null) {
                predicates.add(cb.equal(addressJoin.get("street"), criteria.getStreet()));
            }
            if (criteria.getZipcode() != null) {
                predicates.add(cb.equal(addressJoin.get("zipcode"), criteria.getZipcode()));
            }
            if (criteria.getStartDate() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("date"), criteria.getStartDate()));
            }
            if (criteria.getEndDate() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("date"), criteria.getEndDate()));
            }
            if (criteria.getPaymentMethod() != null) {
                predicates.add(cb.equal(root.get("payment").type(), criteria.getPaymentMethod()));
            }
            if (criteria.getCustomerName() != null) {
                predicates.add(cb.equal(customerJoin.get("name"), criteria.getCustomerName()));
            }
            if (criteria.getOrderStatus() != null) {
                predicates.add(cb.equal(root.get("status"), criteria.getOrderStatus()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
