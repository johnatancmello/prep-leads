package com.prep.api.repository.funcionario.impl.query;

import com.prep.api.model.funcionario.Funcionario;
import com.prep.api.model.funcionario.FuncionarioFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FuncionarioSummaryRepositoryQuery {
    public Page<Funcionario> filter(FuncionarioFilter filter, Pageable pageable);
    public Long getTotal(FuncionarioFilter filter);
}
