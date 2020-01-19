package com.prep.api.repository.meta;

import com.prep.api.model.meta.MetaJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaRepository extends JpaRepository<MetaJPA, Long>, MetaRepositoryQuery {

}
