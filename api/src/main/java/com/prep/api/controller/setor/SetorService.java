package com.prep.api.controller.setor;

import com.prep.api.model.setor.Setor;
import com.prep.api.model.setor.SetorFilter;
import com.prep.api.model.setor.SetorJPA;
import com.prep.api.repository.setor.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SetorService {

    @Autowired
    private SetorRepository repository;

    public Page<Setor> filter(SetorFilter filter, Pageable pageable) {
        return repository.filter(filter, pageable);
    }

    public Setor add(SetorJPA setor){
        return repository.save(setor);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public ResponseEntity<Setor> alterar(SetorJPA setor){
        Optional<SetorJPA> optionalSetorJPA = repository.findById(setor.getId());
        SetorJPA setorJPA = optionalSetorJPA.orElseThrow(() -> new UsernameNotFoundException("Não encontrado!"));
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(setor));
    }
}
