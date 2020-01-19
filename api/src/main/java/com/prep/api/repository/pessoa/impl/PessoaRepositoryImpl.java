package com.prep.api.repository.pessoa.impl;

import com.prep.api.model.pessoa.Pessoa;
import com.prep.api.model.pessoa.PessoaFilter;
import com.prep.api.model.pessoa.PessoaJPA;
import com.prep.api.repository.RepositoryImplPredicateAbstract;
import com.prep.api.repository.pessoa.impl.predicate.PessoaRepositoryImplPredicate;
import com.prep.api.repository.pessoa.impl.query.PessoaRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class PessoaRepositoryImpl extends PessoaRepositoryImplAbstract
        implements PessoaRepositoryQuery {

    private static final Class CLASS = PessoaJPA.class;
    private static final PessoaRepositoryImplPredicate
            PREDICATE = new PessoaRepositoryImplPredicate();

    @Override
    public Page<Pessoa> filter(PessoaFilter filter, Pageable pageable) {
        return super.filter(filter, pageable, CLASS);
    }

    @Override
    public Long getTotal(PessoaFilter filter) {
        return super.getTotal(filter, CLASS);
    }

    protected RepositoryImplPredicateAbstract getPredicates() {
        return PREDICATE;
    }
}
