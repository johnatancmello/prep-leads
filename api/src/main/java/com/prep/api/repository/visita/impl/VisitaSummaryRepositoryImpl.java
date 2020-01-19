package com.prep.api.repository.visita.impl;

import com.prep.api.model.visita.Visita;
import com.prep.api.model.visita.VisitaFilter;
import com.prep.api.model.visita.VisitaJPASummary;
import com.prep.api.repository.RepositoryImplPredicateAbstract;
import com.prep.api.repository.visita.impl.predicate.VisitaSummaryRepositoryImplPredicate;
import com.prep.api.repository.visita.impl.query.VisitaSummaryRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class VisitaSummaryRepositoryImpl extends VisitaRepositoryImplAbstract implements VisitaSummaryRepositoryQuery {

    private static  final Class CLASS = VisitaJPASummary.class;
    private static final VisitaSummaryRepositoryImplPredicate
            PREDICATE = new VisitaSummaryRepositoryImplPredicate();

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
