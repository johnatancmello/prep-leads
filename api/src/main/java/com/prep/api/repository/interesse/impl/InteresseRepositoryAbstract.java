package com.prep.api.repository.interesse.impl;

import com.prep.api.model.interesse.Interesse;
import com.prep.api.model.interesse.InteresseFilter;
import com.prep.api.repository.RepositoryImplAbstract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class InteresseRepositoryAbstract extends RepositoryImplAbstract {

    public Page<Interesse> filter(InteresseFilter filter, Pageable pageable, Class aClass) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery(aClass);

        Root root = criteria.from(aClass);
        root.fetch("pessoa");
        root.fetch("funcionario");
        root.fetch("modulos");

        Predicate[] predicates = builderWhere(filter, builder, root);

        criteria.where(predicates);

        TypedQuery query = manager.createQuery(criteria);

        addRestrictionsOfPage(query, pageable);

        Long total = getTotal(filter, aClass);

        return new PageImpl(query.getResultList(), pageable, total);
    }

    public Long getTotal(InteresseFilter filter, Class aClass) {
        return super.getTotal(filter, aClass);
    }
}
