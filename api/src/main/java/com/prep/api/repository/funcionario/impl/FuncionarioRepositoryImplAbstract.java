package com.prep.api.repository.funcionario.impl;

import com.prep.api.model.funcionario.Funcionario;
import com.prep.api.model.funcionario.FuncionarioFilter;
import com.prep.api.repository.RepositoryImplAbstract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class FuncionarioRepositoryImplAbstract extends RepositoryImplAbstract {

    public Page<Funcionario> filter(FuncionarioFilter filter, Pageable pageable, Class aClass) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery(aClass);

        Root root = criteria.from(aClass);
        root.fetch("usuario");
        root.fetch("setor"); // TODO: atenção
        root.fetch("pessoa");

        Predicate[] predicates = builderWhere(filter, builder, root);

        criteria.where(predicates);

        TypedQuery query = manager.createQuery(criteria);

        addRestrictionsOfPage(query, pageable);

        //Long total = getTotal(predicates, aClass);
        Long total = getTotal(filter, aClass);

        return new PageImpl(query.getResultList(), pageable, total);
    }


    public Long getTotal(FuncionarioFilter filter, Class aClass) {
        return super.getTotal(filter, aClass);
    }
}
