package com.prep.api.repository.funcionario.impl;

import com.prep.api.model.funcionario.Funcionario;
import com.prep.api.model.funcionario.FuncionarioFilter;
import com.prep.api.model.funcionario.FuncionarioJPA;
import com.prep.api.repository.RepositoryImplPredicateAbstract;
import com.prep.api.repository.funcionario.impl.predicate.FuncionarioRepositoryImplPredicate;
import com.prep.api.repository.funcionario.impl.query.FuncionarioRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepositoryImpl
        extends FuncionarioRepositoryImplAbstract implements FuncionarioRepositoryQuery {

    private static final Class CLASS = FuncionarioJPA.class;
    private static final FuncionarioRepositoryImplPredicate
            PREDICATE = new FuncionarioRepositoryImplPredicate();

    @Override
    public Page<Funcionario> filter(FuncionarioFilter filter, Pageable pageable) {
        return super.filter(filter, pageable, CLASS);
    }

    @Override
    public Long getTotal(FuncionarioFilter filter) {
        return super.getTotal(filter, CLASS);
    }

    protected RepositoryImplPredicateAbstract getPredicates() {
        return PREDICATE;
    }

    public FuncionarioJPA findByIdUsuario(String idUsuario) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<FuncionarioJPA> criteria = builder.createQuery(FuncionarioJPA.class);

        Root root = criteria.from(FuncionarioJPA.class);
        root.fetch("usuario");
        root.fetch("setor");
        root.fetch("pessoa");

        List<Predicate> predicates = new ArrayList<>();

        PREDICATE.addIdUsuario(idUsuario, builder, root, predicates);

        criteria.where(predicates.toArray(new Predicate[predicates.size()]));

        TypedQuery<FuncionarioJPA> query = manager.createQuery(criteria);

        //return query.getSingleResult();

        List<FuncionarioJPA> found = query.getResultList();

        if (found.isEmpty()) {
            return null; //or throw checked exception data not found
        } else {
            return found.get(0);
        }
    }
}
