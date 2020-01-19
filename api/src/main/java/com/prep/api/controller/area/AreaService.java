package com.prep.api.controller.area;

import com.prep.api.model.area.Area;
import com.prep.api.model.area.AreaFilter;
import com.prep.api.repository.area.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AreaService {

    @Autowired
    private AreaRepository repository;

    //@Autowired
    //private FormacaoSummaryRepository summaryRepository;

    public Page<Area> filter(AreaFilter filter, Pageable pageable) {
        return repository.filter(filter, pageable);
    }

    /*public Page<Formacao> filterInSummary(FormacaoFilter filter, Pageable pageable) {
        return summaryRepository.filter(filter, pageable);
    }*/

}
