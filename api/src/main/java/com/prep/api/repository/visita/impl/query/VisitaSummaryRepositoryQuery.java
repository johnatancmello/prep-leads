package com.prep.api.repository.visita.impl.query;

import com.prep.api.model.visita.Visita;
import com.prep.api.model.visita.VisitaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VisitaSummaryRepositoryQuery {
    public Page<Visita> filter(VisitaFilter filter, Pageable pageable);
    public Long getTotal(VisitaFilter filter);
}
