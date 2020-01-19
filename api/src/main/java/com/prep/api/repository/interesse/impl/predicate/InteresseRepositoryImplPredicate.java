package com.prep.api.repository.interesse.impl.predicate;

import com.prep.api.model.ModelAbstract;
import com.prep.api.model.interesse.InteresseFilter;
import com.prep.api.model.interesse.InteresseJPA;
import com.prep.api.model.pessoa.PessoaJPA;
import com.prep.api.repository.RepositoryImplPredicateAbstract;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class InteresseRepositoryImplPredicate extends RepositoryImplPredicateAbstract {

    public void addpredicates(ModelAbstract filter, CriteriaBuilder builder, Root root,
                              List<Predicate> predicates) {
        InteresseFilter interesseFilter = (InteresseFilter) filter;
        addId(interesseFilter.getId(), builder, root, predicates);
        addIdPessoa(interesseFilter.getIdPessoa(), builder, root, predicates);
    }

    public void addIdPessoa(Long idPessoa, CriteriaBuilder builder, Root root,
                            List<Predicate> predicates) {
        if (idPessoa != null) {
            Join<InteresseJPA, PessoaJPA> join = root.join("pessoa");
            predicates.add(builder.equal(join.get("id"), idPessoa));
        }
    }

}
