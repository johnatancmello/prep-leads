package com.prep.api.repository.aluno;

import com.prep.api.model.aluno.AlunoJPA;
import com.prep.api.repository.aluno.impl.query.AlunoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlunoRepository
		extends JpaRepository<AlunoJPA, Long>,
		AlunoRepositoryQuery {
}
