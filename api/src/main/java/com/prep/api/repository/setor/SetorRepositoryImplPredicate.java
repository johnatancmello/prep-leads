package com.prep.api.repository.setor;

import com.prep.api.model.ModelAbstract;
import com.prep.api.model.setor.SetorFilter;
import com.prep.api.repository.RepositoryImplPredicateAbstract;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class SetorRepositoryImplPredicate extends RepositoryImplPredicateAbstract {

    public void addpredicates(ModelAbstract filter, CriteriaBuilder builder, Root root,
                              List<Predicate> predicates) {
        SetorFilter setorFilter = (SetorFilter) filter;
        addId(setorFilter.getId(), builder, root, predicates);
        addNome(setorFilter.getNome(), builder, root, predicates);
    }

}
