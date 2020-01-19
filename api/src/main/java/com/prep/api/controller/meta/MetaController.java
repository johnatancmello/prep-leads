package com.prep.api.controller.meta;

import com.prep.api.model.meta.MetaFilter;
import com.prep.api.model.meta.MetaJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metas")
public class MetaController {

    @Autowired
    private MetaService metaService;

    @GetMapping
    public Page<MetaJPA> filter(MetaFilter filter, Pageable pageable) {
        return metaService.filter(filter, pageable);
    }
}
