package com.prep.api.repository.interesse.impl;

import com.prep.api.model.interesse.Interesse;
import com.prep.api.model.interesse.InteresseFilter;
import com.prep.api.model.interesse.InteresseJPA;
import com.prep.api.repository.RepositoryImplPredicateAbstract;
import com.prep.api.repository.interesse.impl.predicate.InteresseRepositoryImplPredicate;
import com.prep.api.repository.interesse.impl.query.InteresseRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class InteresseRepositoryImpl
        extends InteresseRepositoryAbstract implements InteresseRepositoryQuery {

    private static  final Class CLASS = InteresseJPA.class;
    private static final InteresseRepositoryImplPredicate
            PREDICATE = new InteresseRepositoryImplPredicate();

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
