package com.prep.api.repository.interesse.impl.query;

import com.prep.api.model.interesse.Interesse;
import com.prep.api.model.interesse.InteresseFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InteresseRepositoryQuery {
    public Page<Interesse> filter(InteresseFilter filter, Pageable pageable);
    public Long getTotal(InteresseFilter filter);
}
