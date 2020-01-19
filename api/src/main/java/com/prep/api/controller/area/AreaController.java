package com.prep.api.controller.area;

import com.prep.api.model.area.Area;
import com.prep.api.model.area.AreaFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/areas")
public class AreaController {

    @Autowired
    private AreaService service;

    @GetMapping
    public Page<Area> filter(AreaFilter filter, Pageable pageable) {
        return service.filter(filter, pageable);
    }

    /*@GetMapping("/summary")
    public Page<Formacao> filterInSummary(FormacaoFilter filter, Pageable pageable) {
        return service.filterInSummary(filter, pageable);
    }*/

}
