package com.prep.api.repository.meta;

import com.prep.api.model.ModelAbstract;
import com.prep.api.model.meta.MetaFilter;
import com.prep.api.repository.RepositoryImplPredicateAbstract;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class MetaRepositoryImplPredicate extends RepositoryImplPredicateAbstract {

    public void addpredicates(ModelAbstract filter, CriteriaBuilder builder, Root root,
                                     List<Predicate> predicates) {
        MetaFilter metaFilter = (MetaFilter) filter;
        addId(metaFilter.getId(), builder, root, predicates);
        addNome(metaFilter.getNome(), builder, root, predicates);
        addDescricao(metaFilter.getDescricao(), builder, root, predicates);
        addPeriodo(metaFilter.getPeriodo(), builder, root, predicates);
    }

    public void addDescricao(String descricao, CriteriaBuilder builder, Root root,
                                  List<Predicate> predicates) {
        if (!StringUtils.isEmpty(descricao)) {
            predicates.add(builder.like(builder.upper(root.get("descricao")), descricao.toUpperCase()));
        }
    }

    public void addPeriodo(Integer periodo, CriteriaBuilder builder, Root root,
                                         List<Predicate> predicates) {
        if (periodo != null) {
            predicates.add(builder.equal(root.get("periodo"), periodo));
        }
    }

}
