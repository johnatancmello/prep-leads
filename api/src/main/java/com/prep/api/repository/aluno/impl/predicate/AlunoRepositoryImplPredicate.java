package com.prep.api.repository.aluno.impl.predicate;

import com.prep.api.model.ModelAbstract;
import com.prep.api.model.aluno.AlunosFilter;
import com.prep.api.model.funcionario.FuncionarioJPA;
import com.prep.api.model.pessoa.PessoaJPA;
import com.prep.api.repository.pessoa.impl.predicate.PessoaRepositoryImplPredicate;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class AlunoRepositoryImplPredicate extends PessoaRepositoryImplPredicate {

    public void addpredicates(ModelAbstract filter, CriteriaBuilder builder, Root root,
                              List<Predicate> predicates) {
        AlunosFilter alunosFilter = (AlunosFilter) filter;
        addId(alunosFilter.getId(), builder, root, predicates);
        addIdPessoa(alunosFilter.getIdPessoa(), builder, root, predicates);
        addStatus(alunosFilter.getStatus(), builder, root, predicates);
    }

    public void addIdPessoa(Long idPessoa, CriteriaBuilder builder, Root root,
                             List<Predicate> predicates) {
        if (idPessoa != null) {
            Join<FuncionarioJPA, PessoaJPA> join = root.join("pessoa");
            predicates.add(builder.equal(join.get("id"), idPessoa));
        }
    }

    public void addStatus(String status , CriteriaBuilder builder, Root root,
                                  List<Predicate> predicates) {
        if (!StringUtils.isEmpty(status)) {
            predicates.add(builder.like(builder.upper(root.get("status")), status.toUpperCase()));
        }
    }



}
