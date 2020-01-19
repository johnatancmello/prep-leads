package com.prep.api.controller.interesse;

import com.prep.api.model.interesse.Interesse;
import com.prep.api.model.interesse.InteresseFilter;
import com.prep.api.model.interesse.InteresseJPA;
import com.prep.api.repository.interesse.InteresseRepository;
import com.prep.api.repository.interesse.InteresseSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InteresseService {

    @Autowired
    private InteresseRepository repository;

    @Autowired
    private InteresseSummaryRepository summaryRepository;

    public Page<Interesse> filter(InteresseFilter filter, Pageable pageable){
        return repository.filter(filter, pageable);
    }

    public Page<Interesse> filterInSummary(InteresseFilter filter, Pageable pageable){
        return summaryRepository.filter(filter, pageable);
    }

    public ResponseEntity<Interesse> findById(Long id) {
        return ResponseEntity.ok(repository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Não encontrado!")));
    }

    public ResponseEntity<Interesse> add(InteresseJPA interesse) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(interesse));
    }

    public ResponseEntity<Interesse> alterar(InteresseJPA interesse){
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(interesse));
    }
}
