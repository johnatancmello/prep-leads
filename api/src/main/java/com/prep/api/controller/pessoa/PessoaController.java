package com.prep.api.controller.pessoa;

import com.prep.api.model.pessoa.Pessoa;
import com.prep.api.model.pessoa.PessoaFilter;
import com.prep.api.model.pessoa.PessoaJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;


    @GetMapping
    public Page<Pessoa> filter(PessoaFilter filter, Pageable pageable) {
        return service.filter(filter, pageable);
    }

    @GetMapping("/summary")
    public Page<Pessoa> filterInSummmary(PessoaFilter filter, Pageable pageable) {
        return service.filterInSummmary(filter, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaJPA> getImagem(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<PessoaJPA> add(@Valid @RequestBody PessoaJPA pessoaJPA){
    return service.add(pessoaJPA);
    }

    @PutMapping
    public ResponseEntity<Pessoa> alterar(@Valid @RequestBody PessoaJPA funcionario) {
        return service.alterar(funcionario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }
}
