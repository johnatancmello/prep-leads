package com.prep.api.repository.imagem;

import com.prep.api.model.imagem.ImagemJPASummary;
import com.prep.api.repository.imagem.impl.query.ImagemSummaryRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagemSummaryRepository
        extends JpaRepository<ImagemJPASummary, Long>, ImagemSummaryRepositoryQuery {

}
