package com.prep.api.repository.pessoa.impl;

import com.prep.api.model.pessoa.Pessoa;
import com.prep.api.model.pessoa.PessoaFilter;
import com.prep.api.model.pessoa.PessoaJPASummary;
import com.prep.api.repository.RepositoryImplPredicateAbstract;
import com.prep.api.repository.pessoa.impl.predicate.PessoaSummaryRepositoryImplPredicate;
import com.prep.api.repository.pessoa.impl.query.PessoaSummaryRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class PessoaSummaryRepositoryImpl extends PessoaRepositoryImplAbstract
        implements PessoaSummaryRepositoryQuery {

    private static  final Class CLASS = PessoaJPASummary.class;
    private static final PessoaSummaryRepositoryImplPredicate
            PREDICATE = new PessoaSummaryRepositoryImplPredicate();

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