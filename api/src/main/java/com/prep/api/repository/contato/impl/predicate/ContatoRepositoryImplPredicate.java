package com.prep.api.repository.contato.impl.predicate;

import com.prep.api.model.ModelAbstract;
import com.prep.api.model.contato.ContatoFilter;
import com.prep.api.model.contato.ContatoJPA;
import com.prep.api.model.pessoa.PessoaJPA;
import com.prep.api.repository.RepositoryImplPredicateAbstract;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ContatoRepositoryImplPredicate extends RepositoryImplPredicateAbstract {
    public void addpredicates(ModelAbstract filter, CriteriaBuilder builder, Root root,
                              List<Predicate> predicates) {
        ContatoFilter contatoFilter = (ContatoFilter) filter;
        addId(contatoFilter.getId(), builder, root, predicates);
        addStatus(contatoFilter.getStatus(), builder, root, predicates);
        addIdPessoa(contatoFilter.getIdPessoa(), builder, root, predicates);
    }

    public void addIdPessoa(Long idPessoa, CriteriaBuilder builder, Root root,
                            List<Predicate> predicates) {
        if (idPessoa != null) {
            Join<ContatoJPA, PessoaJPA> join = root.join("pessoa");
            predicates.add(builder.equal(join.get("id"), idPessoa));
        }
    }

}