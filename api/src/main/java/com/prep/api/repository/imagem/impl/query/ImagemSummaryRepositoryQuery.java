package com.prep.api.repository.imagem.impl.query;

import com.prep.api.model.imagem.Imagem;
import com.prep.api.model.imagem.ImagemFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ImagemSummaryRepositoryQuery {
    public Page<Imagem> filter(ImagemFilter filter, Pageable pageable);
    public Long getTotal(ImagemFilter filter);
}
