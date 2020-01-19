package com.prep.api.repository.funcionario.impl.predicate;

import com.prep.api.model.ModelAbstract;
import com.prep.api.model.funcionario.FuncionarioFilter;
import com.prep.api.model.funcionario.FuncionarioJPA;
import com.prep.api.model.pessoa.PessoaJPA;
import com.prep.api.model.usuario.UsuarioJPA;
import com.prep.api.repository.pessoa.impl.predicate.PessoaRepositoryImplPredicate;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class FuncionarioRepositoryImplPredicate extends PessoaRepositoryImplPredicate {

    public void addpredicates(ModelAbstract filter, CriteriaBuilder builder, Root root,
                              List<Predicate> predicates) {
        FuncionarioFilter funcionarioFilter = (FuncionarioFilter) filter;
        addId(funcionarioFilter.getId(), builder, root, predicates);
        addIdUsuario(funcionarioFilter.getIdUsuario(), builder, root, predicates);
        addIdPessoa(funcionarioFilter.getIdPessoa(), builder, root, predicates);
        addStatus(funcionarioFilter.getStatus(), builder, root, predicates);
    }

    public void addIdUsuario(String idUsuario, CriteriaBuilder builder, Root root,
                                     List<Predicate> predicates) {
        if (!StringUtils.isEmpty(idUsuario)) {
            Join<FuncionarioJPA, UsuarioJPA> join = root.join("usuario");
            predicates.add(builder.equal(join.get("idUsuario"), idUsuario));
        }
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

    /*private static void addUsuario(FuncionarioFilter filter, CriteriaBuilder builder, Root root,
                                  List<Predicate> predicates) {
        if (!StringUtils.isEmpty(filter.getIdUsuario())) {
            // Join
            Join<FuncionarioJPA, UsuarioJPA> join = root.join("usuario");
            predicates.add(builder.like(builder.lower(join.get("idUsuario")), filter.getIdUsuario()));
        }
    }*/


}
