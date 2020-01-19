package com.prep.api.repository.funcionario.impl;

import com.prep.api.model.funcionario.Funcionario;
import com.prep.api.model.funcionario.FuncionarioFilter;
import com.prep.api.model.funcionario.FuncionarioJPASummary;
import com.prep.api.repository.RepositoryImplPredicateAbstract;
import com.prep.api.repository.funcionario.impl.predicate.FuncionarioSummaryRepositoryImplPredicate;
import com.prep.api.repository.funcionario.impl.query.FuncionarioSummaryRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class FuncionarioSummaryRepositoryImpl
        extends FuncionarioRepositoryImplAbstract
        implements FuncionarioSummaryRepositoryQuery {

    private static final Class CLASS = FuncionarioJPASummary.class;
    private static final FuncionarioSummaryRepositoryImplPredicate
            PREDICATE = new FuncionarioSummaryRepositoryImplPredicate();

    @Override
    public Page<Funcionario> filter(FuncionarioFilter filter, Pageable pageable) {
        return super.filter(filter, pageable, CLASS);
    }

    @Override
    public Long getTotal(FuncionarioFilter filter) {
        return super.getTotal(filter, CLASS);
    }

    protected RepositoryImplPredicateAbstract getPredicates() {
        return PREDICATE;
    }

}
