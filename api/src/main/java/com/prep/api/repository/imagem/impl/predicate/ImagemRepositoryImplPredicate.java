package com.prep.api.repository.imagem.impl.predicate;

import com.prep.api.model.ModelAbstract;
import com.prep.api.model.imagem.ImagemFilter;
import com.prep.api.repository.RepositoryImplPredicateAbstract;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ImagemRepositoryImplPredicate extends RepositoryImplPredicateAbstract {

    public void addpredicates(ModelAbstract filter, CriteriaBuilder builder, Root root,
                                     List<Predicate> predicates) {
        ImagemFilter imagemFilter = (ImagemFilter) filter;
        addId(imagemFilter.getId(), builder, root, predicates);
        addNome(imagemFilter.getNome(), builder, root, predicates);
    }
}
