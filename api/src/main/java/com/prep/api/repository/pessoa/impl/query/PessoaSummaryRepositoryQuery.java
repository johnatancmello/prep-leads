package com.prep.api.repository.pessoa.impl.query;

import com.prep.api.model.pessoa.Pessoa;
import com.prep.api.model.pessoa.PessoaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaSummaryRepositoryQuery {
    public Page<Pessoa> filter(PessoaFilter filter, Pageable pageable);
    public Long getTotal(PessoaFilter filter);
}
