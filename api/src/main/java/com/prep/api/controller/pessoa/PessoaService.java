package com.prep.api.controller.pessoa;

import com.prep.api.model.pessoa.Pessoa;
import com.prep.api.model.pessoa.PessoaFilter;
import com.prep.api.model.pessoa.PessoaJPA;
import com.prep.api.repository.pessoa.PessoaRepository;
import com.prep.api.repository.pessoa.PessoaSummaryRepository;
import com.prep.api.repository.visita.VisitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    @Autowired
    private VisitaRepository visitaRepository;

    @Autowired
    private PessoaSummaryRepository pessoaSummaryRepository;

    public Page<Pessoa> filter(PessoaFilter filter, Pageable pageable) {
        return repository.filter(filter, pageable);
    }

    public Page<Pessoa> filterInSummmary(PessoaFilter filter, Pageable pageable) {
        return pessoaSummaryRepository.filter(filter, pageable);
    }

    public ResponseEntity<PessoaJPA> add(PessoaJPA pessoaJPA) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(repository.save(pessoaJPA));
    }

    public ResponseEntity<PessoaJPA> findById(Long id) {
        Optional<PessoaJPA>  optionalPessoa = repository.findById(id);
        PessoaJPA pessoaJPA = optionalPessoa.orElseThrow(() -> new UsernameNotFoundException("Não encontrado!"));

        //pessoaJPA.setImagem(pessoaJPA.getImagem());
        return ResponseEntity.ok(pessoaJPA);
    }

    public ResponseEntity<Pessoa> alterar(PessoaJPA funcionario){
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(funcionario));
    }

    public void deleteById(Long id){
        visitaRepository.deleteByPessoaId(id);
        repository.deleteById(id);
    }
}
