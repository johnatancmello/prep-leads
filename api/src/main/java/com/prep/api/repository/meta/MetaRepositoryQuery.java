package com.prep.api.repository.meta;

import com.prep.api.model.meta.MetaFilter;
import com.prep.api.model.meta.MetaJPA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MetaRepositoryQuery {
    public Page<MetaJPA> filter(MetaFilter filter, Pageable pageable);
    public Long getTotal(MetaFilter filter);
}
