package com.prep.api.repository.visita;

import com.prep.api.model.visita.VisitaJPASummary;
import com.prep.api.repository.visita.impl.query.VisitaSummaryRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitaSummaryRepository extends JpaRepository<VisitaJPASummary, Long>
    , VisitaSummaryRepositoryQuery {

}
