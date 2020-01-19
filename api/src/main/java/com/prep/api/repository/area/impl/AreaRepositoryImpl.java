package com.prep.api.repository.area.impl;

import com.prep.api.model.area.Area;
import com.prep.api.model.area.AreaFilter;
import com.prep.api.model.area.AreaJPA;
import com.prep.api.repository.RepositoryImplPredicateAbstract;
import com.prep.api.repository.area.impl.predicate.AreaRepositoryImplPredicate;
import com.prep.api.repository.area.impl.query.AreaRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public class AreaRepositoryImpl
        extends AreaRepositoryImplAbstract implements AreaRepositoryQuery {

    private static  final Class CLASS = AreaJPA.class;
    private static final AreaRepositoryImplPredicate
            PREDICATE = new AreaRepositoryImplPredicate();

    @Override
    public Page<Area> filter(AreaFilter filter, Pageable pageable) {
        return super.filter(filter, pageable, CLASS);
    }

    @Override
    public Long getTotal(AreaFilter filter) {
        return super.getTotal(filter, CLASS);
    }

    protected RepositoryImplPredicateAbstract getPredicates() {
        return PREDICATE;
    }
}
