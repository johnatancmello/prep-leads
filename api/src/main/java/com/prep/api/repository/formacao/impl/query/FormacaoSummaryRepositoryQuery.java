package com.prep.api.repository.formacao.impl.query;

import com.prep.api.model.formacao.Formacao;
import com.prep.api.model.formacao.FormacaoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FormacaoSummaryRepositoryQuery {
    public Page<Formacao> filter(FormacaoFilter filter, Pageable pageable);
    public Long getTotal(FormacaoFilter filter);
}
