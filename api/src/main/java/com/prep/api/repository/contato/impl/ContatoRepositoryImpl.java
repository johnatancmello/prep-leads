package com.prep.api.repository.contato.impl;

import com.prep.api.model.contato.Contato;
import com.prep.api.model.contato.ContatoFilter;
import com.prep.api.model.contato.ContatoJPA;
import com.prep.api.repository.RepositoryImplPredicateAbstract;
import com.prep.api.repository.contato.impl.predicate.ContatoRepositoryImplPredicate;
import com.prep.api.repository.contato.impl.query.ContatoRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ContatoRepositoryImpl extends ContatoRepositoryImplAbstract
        implements ContatoRepositoryQuery {

    private static final Class CLASS = ContatoJPA.class;
    private static final ContatoRepositoryImplPredicate
            PREDICATE = new ContatoRepositoryImplPredicate();

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
