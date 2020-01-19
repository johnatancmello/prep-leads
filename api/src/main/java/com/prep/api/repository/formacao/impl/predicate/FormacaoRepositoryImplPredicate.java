package com.prep.api.repository.formacao.impl.predicate;

import com.prep.api.model.ModelAbstract;
import com.prep.api.model.formacao.FormacaoFilter;
import com.prep.api.repository.RepositoryImplPredicateAbstract;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class FormacaoRepositoryImplPredicate extends RepositoryImplPredicateAbstract {
    public void addpredicates(ModelAbstract filter, CriteriaBuilder builder, Root root,
                              List<Predicate> predicates) {
        FormacaoFilter formacaoFilter = (FormacaoFilter) filter;
        addId(formacaoFilter.getId(), builder, root, predicates);
        addNome(formacaoFilter.getNome(), builder, root, predicates);
    }
}
