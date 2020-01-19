package com.prep.api.repository.pessoa;

import com.prep.api.model.pessoa.PessoaJPASummary;
import com.prep.api.repository.pessoa.impl.query.PessoaSummaryRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaSummaryRepository extends JpaRepository<PessoaJPASummary, Long>,
        PessoaSummaryRepositoryQuery {

}
