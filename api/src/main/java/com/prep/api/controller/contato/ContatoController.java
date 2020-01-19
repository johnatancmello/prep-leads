package com.prep.api.controller.contato;

import com.prep.api.model.contato.Contato;
import com.prep.api.model.contato.ContatoFilter;
import com.prep.api.model.contato.ContatoJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    private ContatoService service;

    @GetMapping
    public Page<Contato> filter(ContatoFilter filter, Pageable pageable) {
        return service.filter(filter, pageable);
    }

    @GetMapping("/summary")
    public Page<Contato> filterInSummary(ContatoFilter filter, Pageable pageable) {
        return service.filterInSummary(filter, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> getImagem(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @Transactional
    //@PreAuthorize("hasAuthority('ROLE_ALTERAR_FORMACOES') and #oauth2.hasScope('write')")
    public ResponseEntity<Contato> add(@Valid @RequestBody ContatoJPA contato) {
        return service.add(contato);
    }
}