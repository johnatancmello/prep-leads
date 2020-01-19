package com.prep.api.repository.imagem.impl;

import com.prep.api.model.imagem.Imagem;
import com.prep.api.model.imagem.ImagemFilter;
import com.prep.api.model.imagem.ImagemJPASummary;
import com.prep.api.repository.RepositoryImplPredicateAbstract;
import com.prep.api.repository.imagem.impl.predicate.ImagemSummaryRepositoryImplPredicate;
import com.prep.api.repository.imagem.impl.query.ImagemRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ImagemSummaryRepositoryImpl extends ImagemRepositoryImplAbstract implements ImagemRepositoryQuery {

    private static final Class CLASS = ImagemJPASummary.class;
    private static final ImagemSummaryRepositoryImplPredicate
            PREDICATE = new ImagemSummaryRepositoryImplPredicate();

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
