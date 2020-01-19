package com.prep.api.repository.interesse;

import com.prep.api.model.interesse.InteresseJPA;
import com.prep.api.repository.interesse.impl.query.InteresseRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteresseRepository
        extends JpaRepository<InteresseJPA, Long>, InteresseRepositoryQuery {
}
