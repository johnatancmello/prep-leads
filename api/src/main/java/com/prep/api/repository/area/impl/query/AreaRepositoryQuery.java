package com.prep.api.repository.area.impl.query;

import com.prep.api.model.area.Area;
import com.prep.api.model.area.AreaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AreaRepositoryQuery {
    public Page<Area> filter(AreaFilter filter, Pageable pageable);
    public Long getTotal(AreaFilter filter);
}
