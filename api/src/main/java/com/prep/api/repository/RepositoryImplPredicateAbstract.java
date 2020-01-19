package com.prep.api.repository;

import com.prep.api.model.ModelAbstract;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class RepositoryImplPredicateAbstract {

    public abstract void addpredicates(ModelAbstract filter, CriteriaBuilder builder, Root root,
                              List<Predicate> predicates);

    public void addId(Long id, CriteriaBuilder builder, Root root,
                             List<Predicate> predicates) {
        if (id != null) {
            predicates.add(builder.equal(root.get("id"), id));
        }
    }

    public void addNome(String nome, CriteriaBuilder builder, Root root,
                               List<Predicate> predicates) {
        if (!StringUtils.isEmpty(nome)) {
            predicates.add(builder.like(builder.upper(root.get("nome")), nome.toUpperCase()));
        }
    }

    public void addStatus(String status, CriteriaBuilder builder, Root root,
                          List<Predicate> predicates) {
        if (!StringUtils.isEmpty(status)) {
            predicates.add(builder.like(builder.upper(root.get("status")), status.toUpperCase()));
        }
    }

}
