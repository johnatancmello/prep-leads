package com.prep.api.repository.meta;

import com.prep.api.model.meta.MetaFilter;
import com.prep.api.model.meta.MetaJPA;
import com.prep.api.repository.RepositoryImplAbstract;
import com.prep.api.repository.RepositoryImplPredicateAbstract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MetaRepositoryImpl extends RepositoryImplAbstract implements MetaRepositoryQuery{

    private static final Class CLASS = MetaJPA.class;

    @Override
    public Page<MetaJPA> filter(MetaFilter filter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery(CLASS);

        Root root = criteria.from(CLASS);

        Predicate[] predicates = builderWhere(filter, builder, root);

        criteria.where(predicates);

        TypedQuery query = manager.createQuery(criteria);

        addRestrictionsOfPage(query, pageable);

        Long total = getTotal(filter, CLASS);

        return new PageImpl(query.getResultList(), pageable, total);
    }

    @Override
    public Long getTotal(MetaFilter filter) {
        return super.getTotal(filter, CLASS);
    }

    protected RepositoryImplPredicateAbstract getPredicates() {
        return null;
    }
}
