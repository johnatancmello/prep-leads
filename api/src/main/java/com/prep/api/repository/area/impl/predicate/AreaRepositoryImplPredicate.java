package com.prep.api.repository.area.impl.predicate;

import com.prep.api.model.ModelAbstract;
import com.prep.api.model.area.AreaFilter;
import com.prep.api.model.area.AreaJPA;
import com.prep.api.repository.RepositoryImplPredicateAbstract;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class AreaRepositoryImplPredicate extends RepositoryImplPredicateAbstract {
    public void addpredicates(ModelAbstract filter, CriteriaBuilder builder, Root root,
                              List<Predicate> predicates) {
        AreaFilter areaFilter = (AreaFilter) filter;
        addId(areaFilter.getId(), builder, root, predicates);
        addNome(areaFilter.getNome(), builder, root, predicates);
        addNomeAreaPrecedente(areaFilter.getNomeAreaPrecedente(), builder, root, predicates);
    }

    public void addNomeAreaPrecedente(String nomeAreaPrecedente, CriteriaBuilder builder, Root root,
                             List<Predicate> predicates) {
        if (!StringUtils.isEmpty(nomeAreaPrecedente)) {
            Join<AreaJPA, AreaJPA> join = root.join("areaPrecedente");
            predicates.add(builder.equal(join.get("nome"), nomeAreaPrecedente));
        }
    }
}
