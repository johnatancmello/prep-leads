package com.prep.api.repository.contato;

import com.prep.api.model.contato.ContatoJPA;
import com.prep.api.repository.contato.impl.query.ContatoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<ContatoJPA, Long>,
        ContatoRepositoryQuery {
}
