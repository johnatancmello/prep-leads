package com.prep.api.controller.aluno;

import com.prep.api.model.aluno.Aluno;
import com.prep.api.model.aluno.AlunoJPA;
import com.prep.api.model.aluno.AlunosFilter;
import com.prep.api.repository.aluno.AlunoRepository;
import com.prep.api.repository.aluno.AlunoSummaryRepository;
import com.prep.api.repository.visita.VisitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private VisitaRepository visitaRepository;

    @Autowired
    private AlunoSummaryRepository summaryRepository;

    public Page<Aluno> filter(AlunosFilter filter, Pageable pageable){
        return repository.filter(filter, pageable);
    }

    public Page<Aluno> filterInSummary(AlunosFilter filter, Pageable pageable){
        return summaryRepository.filter(filter, pageable);
    }

    public ResponseEntity<Aluno> findById(Long id) {
        Optional<AlunoJPA> optional = repository.findById(id);
        return ResponseEntity.ok(optional.orElseThrow(() -> new UsernameNotFoundException("Não encontrado!")));
    }

    public ResponseEntity<Aluno> add(AlunoJPA funcionario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(funcionario));
    }

    public ResponseEntity<Aluno> alterar(AlunoJPA interesse){
        //Funcionario não pode ser alterado REGRA
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(interesse));
    }

    public void deleteById(Long id){
        visitaRepository.deleteByPessoaAlunoId(id);
        repository.deleteById(id);
    }
}
