package com.prep.api.controller.interesse;

import com.prep.api.model.interesse.Interesse;
import com.prep.api.model.interesse.InteresseFilter;
import com.prep.api.model.interesse.InteresseJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/interesses")
public class InteresseController {

    @Autowired
    private InteresseService service;

    @GetMapping
    public Page<Interesse> filter(InteresseFilter filter, Pageable pageable) {
        return service.filter(filter, pageable);
    }

    @GetMapping("/summary")
    public Page<Interesse> filterInSummary(InteresseFilter filter, Pageable pageable) {
        return service.filterInSummary(filter, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Interesse> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('ROLE_ALTERAR_FORMACOES') and #oauth2.hasScope('write')")
    public ResponseEntity<Interesse> add(@Valid @RequestBody InteresseJPA interesse) {
        return service.add(interesse);
    }

    @PutMapping
    public ResponseEntity<Interesse> alterar(@Valid @RequestBody InteresseJPA interesse) {
        return service.alterar(interesse);
    }
}
