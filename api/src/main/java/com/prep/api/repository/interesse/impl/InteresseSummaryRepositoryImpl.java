package com.prep.api.repository.interesse.impl;

import com.prep.api.model.interesse.Interesse;
import com.prep.api.model.interesse.InteresseFilter;
import com.prep.api.model.interesse.InteresseJPASummary;
import com.prep.api.repository.RepositoryImplPredicateAbstract;
import com.prep.api.repository.interesse.impl.predicate.InteresseSummaryRepositoryImplPredicate;
import com.prep.api.repository.interesse.impl.query.InteresseSummaryRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class InteresseSummaryRepositoryImpl
        extends InteresseRepositoryAbstract implements InteresseSummaryRepositoryQuery {

    private static  final Class CLASS = InteresseJPASummary.class;
    private static final InteresseSummaryRepositoryImplPredicate
            PREDICATE = new InteresseSummaryRepositoryImplPredicate();

    @Override
    public Page<Interesse> filter(InteresseFilter filter, Pageable pageable) {
        return super.filter(filter, pageable, CLASS);
    }

    @Override
    public Long getTotal(InteresseFilter filter) {
        return super.getTotal(filter, CLASS);
    }

    protected RepositoryImplPredicateAbstract getPredicates() {
        return PREDICATE;
    }
}
