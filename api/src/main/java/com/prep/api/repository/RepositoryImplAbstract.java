package com.prep.api.repository;

import com.prep.api.model.ModelAbstract;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public abstract class RepositoryImplAbstract {

    @PersistenceContext
    protected EntityManager manager;

    protected abstract RepositoryImplPredicateAbstract getPredicates();

    protected Long getTotal(ModelAbstract filter, Class aClass) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);

        Root root = criteria.from(aClass);

        criteria.where(builderWhere(filter, builder, root));

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    protected void addRestrictionsOfPage(TypedQuery query, Pageable pageable) {
        int pageCurrent, totalRegistryByPage, firstRegistryOfPage;

        pageCurrent = pageable.getPageNumber();
        totalRegistryByPage = pageable.getPageSize();
        firstRegistryOfPage = pageCurrent * totalRegistryByPage;

        query.setFirstResult(firstRegistryOfPage);
        query.setMaxResults(totalRegistryByPage);
    }

    protected Predicate[] builderWhere(ModelAbstract filter, CriteriaBuilder builder, Root root) {
        List<Predicate> predicates = new ArrayList<>();
        getPredicates().addpredicates(filter, builder, root, predicates);
        return predicates.toArray(new Predicate[predicates.size()]);
    }

}
