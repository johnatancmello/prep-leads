package com.prep.api.repository.aluno.impl;

import com.prep.api.model.aluno.Aluno;
import com.prep.api.model.aluno.AlunosFilter;
import com.prep.api.model.aluno.AlunosJPASummary;
import com.prep.api.repository.RepositoryImplPredicateAbstract;
import com.prep.api.repository.aluno.impl.predicate.AlunoSummaryRepositoryImplPredicate;
import com.prep.api.repository.aluno.impl.query.AlunoSummaryRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class AlunoSummaryRepositoryImpl
        extends AlunoRepositoryImplAbstract
        implements AlunoSummaryRepositoryQuery {

    private static final Class CLASS = AlunosJPASummary.class;
    private static final AlunoSummaryRepositoryImplPredicate
            PREDICATE = new AlunoSummaryRepositoryImplPredicate();

    @Override
    public Page<Aluno> filter(AlunosFilter filter, Pageable pageable) {
        return super.filter(filter, pageable, CLASS);
    }

    @Override
    public Long getTotal(AlunosFilter filter) {
        return super.getTotal(filter, CLASS);
    }

    protected RepositoryImplPredicateAbstract getPredicates() {
        return PREDICATE;
    }

}
