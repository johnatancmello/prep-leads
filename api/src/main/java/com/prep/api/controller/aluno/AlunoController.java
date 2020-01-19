package com.prep.api.controller.aluno;

import com.prep.api.model.aluno.Aluno;
import com.prep.api.model.aluno.AlunoJPA;
import com.prep.api.model.aluno.AlunosFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoService service;

    @GetMapping
    public Page<Aluno> filter(AlunosFilter filter, Pageable pageable) {
        return service.filter(filter, pageable);
    }

    @GetMapping("/summary")
    public Page<Aluno> filterInSummary(AlunosFilter filter, Pageable pageable) {
        return service.filterInSummary(filter, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getImagem(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('ROLE_ALTERAR_FORMACOES') and #oauth2.hasScope('write')")
    public ResponseEntity<Aluno> add(@Valid @RequestBody AlunoJPA contato) {
        return service.add(contato);
    }

    @PutMapping
    public ResponseEntity<Aluno> alterar(@Valid @RequestBody AlunoJPA aluno) {
        return service.alterar(aluno);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }
}
