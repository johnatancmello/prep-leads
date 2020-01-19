package com.prep.api.repository.visita;

import com.prep.api.model.visita.VisitaJPA;
import com.prep.api.repository.visita.impl.query.VisitaRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitaRepository  extends JpaRepository<VisitaJPA, Long>,
        VisitaRepositoryQuery {
    public void deleteByPessoaId(Long id);
    public void deleteByPessoaAlunoId(Long id);

}
