package com.prep.api.controller.contato;

import com.prep.api.model.contato.Contato;
import com.prep.api.model.contato.ContatoFilter;
import com.prep.api.model.contato.ContatoJPA;
import com.prep.api.model.contato.ContatoStatus;
import com.prep.api.model.interesse.InteresseJPA;
import com.prep.api.model.interesse.InteresseStatus;
import com.prep.api.model.visita.VisitaStatus;
import com.prep.api.repository.contato.ContatoRepository;
import com.prep.api.repository.contato.ContatoSummaryRepository;
import com.prep.api.repository.interesse.InteresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository repository;

    @Autowired
    private InteresseRepository interesseRepository;

    @Autowired
    private ContatoSummaryRepository summaryRepository;

    public Page<Contato> filter(ContatoFilter filter, Pageable pageable) {
        return repository.filter(filter, pageable);
    }

    public Page<Contato> filterInSummary(ContatoFilter filter, Pageable pageable) {
        return summaryRepository.filter(filter, pageable);
    }

    public ResponseEntity<Contato> add(ContatoJPA contato) {
        setRegraDeNegocio(contato);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(contato));
    }

    public ResponseEntity<Contato> findById(Long id) {
        return ResponseEntity.ok(repository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Não encontrado!")));
    }


    private void setRegraDeNegocio(ContatoJPA contato) {
        if (contato.getStatus().equals(ContatoStatus.SEM_INTERESSE.toString())) {
            changeStatusInteresse(contato.getInteresse().getId(), ContatoStatus.SEM_INTERESSE.toString());
        } else if (contato.getVisita().getStatus().equals(VisitaStatus.AGENDADO.toString())) {
            contato.setStatus(ContatoStatus.AGENDADO.toString());
            changeStatusInteresse(contato.getInteresse().getId(), InteresseStatus.PENDENTE.toString());
            contato.getVisita().setContato(contato);
        }
    }

    private void changeStatusInteresse(Long id, String newStatus) {
        InteresseJPA interesse = interesseRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Não encontrado!"));
        interesse.setStatus(newStatus);
        interesseRepository.save(interesse);
    }


}