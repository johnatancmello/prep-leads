package com.prep.api.repository.setor;

import com.prep.api.model.setor.Setor;
import com.prep.api.model.setor.SetorFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SetorRepositoryQuery {
    public Page<Setor> filter(SetorFilter filter, Pageable pageable);
    public Long getTotal(SetorFilter filter);
}
