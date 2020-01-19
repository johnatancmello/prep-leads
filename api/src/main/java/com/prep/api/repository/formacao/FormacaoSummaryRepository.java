package com.prep.api.repository.formacao;

import com.prep.api.model.formacao.FormacaoJPASummary;
import com.prep.api.repository.formacao.impl.query.FormacaoSummaryRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormacaoSummaryRepository
        extends JpaRepository<FormacaoJPASummary, Long>, FormacaoSummaryRepositoryQuery {
}
