package com.prep.api.repository.aluno.impl.query;

import com.prep.api.model.aluno.Aluno;
import com.prep.api.model.aluno.AlunosFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlunoSummaryRepositoryQuery {
    public Page<Aluno> filter(AlunosFilter filter, Pageable pageable);
    public Long getTotal(AlunosFilter filter);
}
