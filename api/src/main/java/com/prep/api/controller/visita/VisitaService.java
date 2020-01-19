package com.prep.api.controller.visita;

import com.prep.api.model.contato.ContatoJPA;
import com.prep.api.model.contato.ContatoStatus;
import com.prep.api.model.interesse.InteresseJPA;
import com.prep.api.model.interesse.InteresseStatus;
import com.prep.api.model.visita.Visita;
import com.prep.api.model.visita.VisitaFilter;
import com.prep.api.model.visita.VisitaJPA;
import com.prep.api.model.visita.VisitaStatus;
import com.prep.api.repository.contato.ContatoRepository;
import com.prep.api.repository.interesse.InteresseRepository;
import com.prep.api.repository.visita.VisitaRepository;
import com.prep.api.repository.visita.VisitaSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VisitaService {

    @Autowired
    private VisitaRepository repository;

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private InteresseRepository interesseRepository;

    @Autowired
    private VisitaSummaryRepository summaryRepository;

    public Page<Visita> filter(VisitaFilter filter, Pageable pageable){
        return repository.filter(filter, pageable);
    }

    public Page<Visita> filterInSummary(VisitaFilter filter, Pageable pageable){
        return summaryRepository.filter(filter, pageable);
    }

    public ResponseEntity<Visita> findById(Long id) {
        return ResponseEntity.ok(repository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Não encontrado!")));
    }

    public ResponseEntity<Visita> add(VisitaJPA visita) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(visita));
    }

    public ResponseEntity<Visita> alterar(VisitaJPA visita){
        setRegraDeNegocio(visita);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(visita));
    }

    private void setRegraDeNegocio(VisitaJPA visita) {

        if (visita.getStatus().equals(VisitaStatus.NAO_COMPARECEU.toString())) {
            changeStatusContato(visita.getContato().getId(), ContatoStatus.NAO_COMPARECEU.toString());
        } else  if (visita.getStatus().equals(VisitaStatus.SEM_INTERESSE.toString())) {
            changeStatusContato(visita.getContato().getId(), ContatoStatus.COMPARECEU.toString());
            changeStatusInteresse(visita.getContato().getInteresse().getId(), InteresseStatus.SEM_INTERESSE.toString());
        } else  if (visita.getStatus().equals(VisitaStatus.PENSANDO.toString())) {
            changeStatusContato(visita.getContato().getId(), ContatoStatus.COMPARECEU.toString());
        }  else  if (visita.getStatus().equals(VisitaStatus.RETORNO.toString())) {
            changeStatusContato(visita.getContato().getId(), ContatoStatus.COMPARECEU.toString());
        } else  if (visita.getStatus().equals(VisitaStatus.MATRICULADO.toString())) {
            changeStatusContato(visita.getContato().getId(), ContatoStatus.COMPARECEU.toString());
            changeStatusInteresse(visita.getContato().getInteresse().getId(), InteresseStatus.MATRICULADO.toString());
        }
    }

    private void changeStatusContato(Long id, String newStatus) {
        ContatoJPA contato = contatoRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Não encontrado!"));
        contato.setStatus(newStatus);
        contatoRepository.save(contato);
    }

    private void changeStatusInteresse(Long id, String newStatus) {
        InteresseJPA interesse = interesseRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Não encontrado!"));
        interesse.setStatus(newStatus);
        interesseRepository.save(interesse);
    }


}
