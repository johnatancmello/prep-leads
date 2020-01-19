package com.prep.api.repository.contato.impl.query;

import com.prep.api.model.contato.Contato;
import com.prep.api.model.contato.ContatoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContatoRepositoryQuery {
    public Page<Contato> filter(ContatoFilter filter, Pageable pageable);
    public Long getTotal(ContatoFilter filter);
}
