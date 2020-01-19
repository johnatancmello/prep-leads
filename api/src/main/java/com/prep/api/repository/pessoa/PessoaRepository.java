package com.prep.api.repository.pessoa;

import com.prep.api.model.pessoa.PessoaJPA;
import com.prep.api.repository.pessoa.impl.query.PessoaRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<PessoaJPA, Long>,
        PessoaRepositoryQuery {

}
