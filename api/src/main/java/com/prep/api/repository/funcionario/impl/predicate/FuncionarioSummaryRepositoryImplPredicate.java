package com.prep.api.repository.funcionario.impl.predicate;

import com.prep.api.model.funcionario.FuncionarioJPASummary;
import com.prep.api.model.usuario.UsuarioJPA;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class FuncionarioSummaryRepositoryImplPredicate
        extends FuncionarioRepositoryImplPredicate {

    public void addIdUsuario(String idUsuario, CriteriaBuilder builder, Root root,
                                     List<Predicate> predicates) {
        if (!StringUtils.isEmpty(idUsuario)) {
            Join<FuncionarioJPASummary, UsuarioJPA> join = root.join("usuario");
            predicates.add(builder.equal(join.get("idUsuario"), idUsuario));
        }
    }

}
