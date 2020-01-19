package com.prep.api.controller.visita;

import com.prep.api.model.visita.Visita;
import com.prep.api.model.visita.VisitaFilter;
import com.prep.api.model.visita.VisitaJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/visitas")
public class VisitaController {

    @Autowired
    private VisitaService service;

    @GetMapping
    public Page<Visita> filter(VisitaFilter filter, Pageable pageable) {
        return service.filter(filter, pageable);
    }

    @GetMapping("/summary")
    public Page<Visita> filterInSummary(VisitaFilter filter, Pageable pageable) {
        return service.filterInSummary(filter, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visita> getImagem(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('ROLE_ALTERAR_FORMACOES') and #oauth2.hasScope('write')")
    public ResponseEntity<Visita> add(@Valid @RequestBody VisitaJPA visita) {
        return service.add(visita);
    }

    @PutMapping
    public ResponseEntity<Visita> alterar(@Valid @RequestBody VisitaJPA visita) {
        return service.alterar(visita);
    }
}
