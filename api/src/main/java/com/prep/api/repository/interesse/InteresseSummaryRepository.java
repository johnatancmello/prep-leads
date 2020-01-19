package com.prep.api.repository.interesse;

import com.prep.api.model.interesse.InteresseJPASummary;
import com.prep.api.repository.interesse.impl.query.InteresseSummaryRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteresseSummaryRepository
        extends JpaRepository<InteresseJPASummary, Long>, InteresseSummaryRepositoryQuery {
}
