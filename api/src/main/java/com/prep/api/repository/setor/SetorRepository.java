package com.prep.api.repository.setor;

import com.prep.api.model.setor.SetorJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SetorRepository extends JpaRepository<SetorJPA, Long>, SetorRepositoryQuery {
}
