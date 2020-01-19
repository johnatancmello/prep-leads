package com.prep.api.repository.area.impl;

import com.prep.api.model.area.Area;
import com.prep.api.model.area.AreaFilter;
import com.prep.api.repository.RepositoryImplAbstract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class AreaRepositoryImplAbstract extends RepositoryImplAbstract {

    public Page<Area> filter(AreaFilter filter, Pageable pageable, Class aClass) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery(aClass);

        Root root = criteria.from(aClass);

        Predicate[] predicates = builderWhere(filter, builder, root);

        criteria.where(predicates);

        TypedQuery query = manager.createQuery(criteria);

        addRestrictionsOfPage(query, pageable);

        Long total = getTotal(filter, aClass);

        return new PageImpl(query.getResultList(), pageable, total);
    }

    public Long getTotal(AreaFilter filter, Class aClass) {
        return super.getTotal(filter, aClass);
    }
}