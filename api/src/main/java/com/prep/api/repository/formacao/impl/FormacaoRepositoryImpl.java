package com.prep.api.repository.formacao.impl;

import com.prep.api.model.formacao.Formacao;
import com.prep.api.model.formacao.FormacaoFilter;
import com.prep.api.model.formacao.FormacaoJPA;
import com.prep.api.repository.RepositoryImplPredicateAbstract;
import com.prep.api.repository.formacao.impl.predicate.FormacaoRepositoryImplPredicate;
import com.prep.api.repository.formacao.impl.query.FormacaoRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class FormacaoRepositoryImpl
        extends FormacaoRepositoryImplAbstract implements FormacaoRepositoryQuery {

    private static  final Class CLASS = FormacaoJPA.class;
    private static final FormacaoRepositoryImplPredicate
            PREDICATE = new FormacaoRepositoryImplPredicate();

    @Override
    public Page<Formacao> filter(FormacaoFilter filter, Pageable pageable) {
        return super.filter(filter, pageable, CLASS);
    }

    @Override
    public Long getTotal(FormacaoFilter filter) {
        return super.getTotal(filter, CLASS);
    }

    protected RepositoryImplPredicateAbstract getPredicates() {
        return PREDICATE;
    }
}
