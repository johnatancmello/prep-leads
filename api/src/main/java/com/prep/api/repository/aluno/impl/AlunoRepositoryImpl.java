package com.prep.api.repository.aluno.impl;

import com.prep.api.model.aluno.Aluno;
import com.prep.api.model.aluno.AlunoJPA;
import com.prep.api.model.aluno.AlunosFilter;
import com.prep.api.repository.RepositoryImplPredicateAbstract;
import com.prep.api.repository.aluno.impl.predicate.AlunoRepositoryImplPredicate;
import com.prep.api.repository.aluno.impl.query.AlunoRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class AlunoRepositoryImpl
        extends AlunoRepositoryImplAbstract implements AlunoRepositoryQuery {

    private static final Class CLASS = AlunoJPA.class;
    private static final AlunoRepositoryImplPredicate
            PREDICATE = new AlunoRepositoryImplPredicate();

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
