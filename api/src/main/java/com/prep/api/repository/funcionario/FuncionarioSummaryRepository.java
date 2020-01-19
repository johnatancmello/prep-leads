package com.prep.api.repository.funcionario;

import com.prep.api.model.funcionario.FuncionarioJPASummary;
import com.prep.api.repository.funcionario.impl.query.FuncionarioSummaryRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioSummaryRepository
        extends JpaRepository<FuncionarioJPASummary, Long>,
        FuncionarioSummaryRepositoryQuery {
}
