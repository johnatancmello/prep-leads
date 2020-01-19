package com.prep.api.repository.area;

import com.prep.api.model.area.AreaJPA;
import com.prep.api.repository.area.impl.query.AreaRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepository extends JpaRepository<AreaJPA, Long>, AreaRepositoryQuery {
}
