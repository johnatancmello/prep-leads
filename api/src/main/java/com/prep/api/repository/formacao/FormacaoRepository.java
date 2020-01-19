package com.prep.api.repository.formacao;

import com.prep.api.model.formacao.FormacaoJPA;
import com.prep.api.repository.formacao.impl.query.FormacaoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormacaoRepository
        extends JpaRepository<FormacaoJPA, Long>, FormacaoRepositoryQuery {
}
