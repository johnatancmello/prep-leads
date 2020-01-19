package com.prep.api.controller.formacao;

import com.prep.api.model.formacao.Formacao;
import com.prep.api.model.formacao.FormacaoFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/formacoes")
public class FormacaoController {

    @Autowired
    private FormacaoService service;

    @GetMapping
    public Page<Formacao> filter(FormacaoFilter filter, Pageable pageable) {
        return service.filter(filter, pageable);
    }

    @GetMapping("/summary")
    public Page<Formacao> filterInSummary(FormacaoFilter filter, Pageable pageable) {
        return service.filterInSummary(filter, pageable);
    }
}
