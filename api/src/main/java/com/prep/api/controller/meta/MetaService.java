package com.prep.api.controller.meta;

import com.prep.api.model.meta.MetaFilter;
import com.prep.api.model.meta.MetaJPA;
import com.prep.api.repository.meta.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MetaService {
    @Autowired
    private MetaRepository metaRepository;

    public Page<MetaJPA> filter(MetaFilter filter, Pageable pageable) {
        return metaRepository.filter(filter, pageable);
    }
}
