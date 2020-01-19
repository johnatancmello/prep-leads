package com.prep.api.repository.imagem;

import com.prep.api.model.imagem.ImagemJPA;
import com.prep.api.repository.imagem.impl.query.ImagemRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagemRepository extends JpaRepository<ImagemJPA, Long>, ImagemRepositoryQuery {
}
