package com.prep.api.repository.imagem.impl;

import com.prep.api.model.imagem.Imagem;
import com.prep.api.model.imagem.ImagemFilter;
import com.prep.api.model.imagem.ImagemJPA;
import com.prep.api.repository.RepositoryImplPredicateAbstract;
import com.prep.api.repository.imagem.impl.predicate.ImagemRepositoryImplPredicate;
import com.prep.api.repository.imagem.impl.query.ImagemRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ImagemRepositoryImpl extends ImagemRepositoryImplAbstract implements ImagemRepositoryQuery {

    private static final Class CLASS = ImagemJPA.class;
    private static final ImagemRepositoryImplPredicate
            PREDICATE = new ImagemRepositoryImplPredicate();

    @Override
    public Page<Imagem> filter(ImagemFilter filter, Pageable pageable) {
        return super.filter(filter, pageable, CLASS);
    }

    @Override
    public Long getTotal(ImagemFilter filter) {
        return super.getTotal(filter, CLASS);
    }

    protected RepositoryImplPredicateAbstract getPredicates() {
        return PREDICATE;
    }
}
