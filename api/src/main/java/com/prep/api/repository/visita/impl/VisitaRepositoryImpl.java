package com.prep.api.repository.visita.impl;

import com.prep.api.model.visita.Visita;
import com.prep.api.model.visita.VisitaFilter;
import com.prep.api.model.visita.VisitaJPA;
import com.prep.api.repository.RepositoryImplPredicateAbstract;
import com.prep.api.repository.visita.impl.predicate.VisitaRepositoryImplPredicate;
import com.prep.api.repository.visita.impl.query.VisitaRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class VisitaRepositoryImpl extends VisitaRepositoryImplAbstract
        implements VisitaRepositoryQuery {

    private static final Class CLASS = VisitaJPA.class;
    private static final VisitaRepositoryImplPredicate
            PREDICATE = new VisitaRepositoryImplPredicate();

    @Override
    public Page<Visita> filter(VisitaFilter filter, Pageable pageable) {
        return super.filter(filter, pageable, CLASS);
    }

    @Override
    public Long getTotal(VisitaFilter filter) {
        return super.getTotal(filter, CLASS);
    }

    protected RepositoryImplPredicateAbstract getPredicates() {
        return PREDICATE;
    }
}

