package com.prep.api.repository.visita.impl.predicate;

import com.prep.api.model.ModelAbstract;
import com.prep.api.model.pessoa.PessoaJPA;
import com.prep.api.model.visita.VisitaFilter;
import com.prep.api.model.visita.VisitaJPA;
import com.prep.api.repository.RepositoryImplPredicateAbstract;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class VisitaRepositoryImplPredicate extends RepositoryImplPredicateAbstract {
    public void addpredicates(ModelAbstract filter, CriteriaBuilder builder, Root root,
                              List<Predicate> predicates) {
        VisitaFilter contatofilter = (VisitaFilter) filter;
        addId(contatofilter.getId(), builder, root, predicates);
        addStatus(contatofilter.getStatus(), builder, root, predicates);
        addIdPessoa(contatofilter.getIdPessoa(), builder, root, predicates);
    }

    public void addIdPessoa(Long idPessoa, CriteriaBuilder builder, Root root,
                            List<Predicate> predicates) {
        if (idPessoa != null) {
            Join<VisitaJPA, PessoaJPA> join = root.join("pessoa");
            predicates.add(builder.equal(join.get("id"), idPessoa));
        }
    }

}