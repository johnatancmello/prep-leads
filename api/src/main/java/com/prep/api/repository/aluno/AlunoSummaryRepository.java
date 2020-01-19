package com.prep.api.repository.aluno;

import com.prep.api.model.aluno.AlunosJPASummary;
import com.prep.api.repository.aluno.impl.query.AlunoSummaryRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoSummaryRepository
        extends JpaRepository<AlunosJPASummary, Long>,
        AlunoSummaryRepositoryQuery {
}
