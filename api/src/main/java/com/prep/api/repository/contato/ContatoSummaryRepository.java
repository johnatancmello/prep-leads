package com.prep.api.repository.contato;

import com.prep.api.model.contato.ContatoJPASummary;
import com.prep.api.repository.contato.impl.query.ContatoSummaryRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoSummaryRepository extends JpaRepository<ContatoJPASummary, Long>, ContatoSummaryRepositoryQuery {
}
