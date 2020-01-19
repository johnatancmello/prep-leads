package com.prep.api.repository.contato.impl;

import com.prep.api.model.contato.Contato;
import com.prep.api.model.contato.ContatoFilter;
import com.prep.api.model.contato.ContatoJPASummary;
import com.prep.api.repository.RepositoryImplPredicateAbstract;
import com.prep.api.repository.contato.impl.predicate.ContatoSummaryRepositoryImplPredicate;
import com.prep.api.repository.contato.impl.query.ContatoSummaryRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ContatoSummaryRepositoryImpl extends ContatoRepositoryImplAbstract implements ContatoSummaryRepositoryQuery {

    private static  final Class CLASS = ContatoJPASummary.class;
    private static final ContatoSummaryRepositoryImplPredicate
            PREDICATE = new ContatoSummaryRepositoryImplPredicate();

    @Override
    public Page<Contato> filter(ContatoFilter filter, Pageable pageable) {
        return super.filter(filter, pageable, CLASS);
    }

    @Override
    public Long getTotal(ContatoFilter filter) {
        return super.getTotal(filter, CLASS);
    }

    protected RepositoryImplPredicateAbstract getPredicates() {
        return PREDICATE;
    }
}
