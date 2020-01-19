package com.prep.api.controller.formacao;

import com.prep.api.model.formacao.Formacao;
import com.prep.api.model.formacao.FormacaoFilter;
import com.prep.api.repository.formacao.FormacaoRepository;
import com.prep.api.repository.formacao.FormacaoSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FormacaoService {

    @Autowired
    private FormacaoRepository repository;

    @Autowired
    private FormacaoSummaryRepository summaryRepository;

    public Page<Formacao> filter(FormacaoFilter filter, Pageable pageable) {
        return repository.filter(filter, pageable);
    }

    public Page<Formacao> filterInSummary(FormacaoFilter filter, Pageable pageable) {
        return summaryRepository.filter(filter, pageable);
    }
}
