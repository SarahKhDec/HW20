package org.example.HW20.repository;

import org.example.HW20.entity.Customer;
import org.example.HW20.utils.CustomerSearchQueryCriteriaConsumer;
import org.example.HW20.utils.SearchCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerSearch {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Customer> searchUser(List<SearchCriteria> params) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
        Root<Customer> root = query.from(Customer.class);

        Predicate predicate = builder.conjunction();

        CustomerSearchQueryCriteriaConsumer searchConsumer = new CustomerSearchQueryCriteriaConsumer(predicate, builder, root);
        params.forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        query.where(predicate);

        return entityManager.createQuery(query).getResultList();
    }

}
