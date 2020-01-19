package com.prep.api.controller.setor;

import com.prep.api.model.setor.Setor;
import com.prep.api.model.setor.SetorFilter;
import com.prep.api.model.setor.SetorJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/setores")
public class SetorController {

    @Autowired
    private SetorService service;

    @GetMapping
    public Page<Setor> filter(SetorFilter filter, Pageable pageable) {
        return service.filter(filter, pageable);
    }

    @PostMapping
    public Setor add(@Valid @RequestBody SetorJPA setor){
        return service.add(setor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Setor> alterar(@Valid @RequestBody SetorJPA setor) {
        return service.alterar(setor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Valid @PathVariable Long id){
        service.deleteById(id);
    }
}
