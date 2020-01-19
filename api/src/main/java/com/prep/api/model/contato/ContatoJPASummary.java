package com.prep.api.model.contato;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prep.api.model.funcionario.FuncionarioJPASummary;
import com.prep.api.model.interesse.InteresseJPA;
import com.prep.api.model.interesse.InteresseJPASummary;
import com.prep.api.model.pessoa.PessoaJPA;
import com.prep.api.model.pessoa.PessoaJPASummary;
import com.prep.api.model.visita.VisitaJPA;
import com.prep.api.model.visita.VisitaJPASummary;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

// Ok

@Entity
@Table(name = "contatos")
@Embeddable
public class ContatoJPASummary extends Contato{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDataDoContato() {
        return dataDoContato;
    }

    public void setDataDoContato(Date dataDoContato) {
        this.dataDoContato = dataDoContato;
    }

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    @JsonIgnore
    public PessoaJPASummary getPessoa() {
        return (PessoaJPASummary) pessoa;
    }

    public void setPessoa(PessoaJPASummary pessoa) {
        this.pessoa = pessoa;
    }

    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_funcionario")
    public FuncionarioJPASummary getFuncionario() {
        return (FuncionarioJPASummary) funcionario;
    }

    public void setFuncionario(FuncionarioJPASummary funcionario) {
        this.funcionario = funcionario;
    }

    @OneToOne
    @JoinColumn(name = "id_interesse")
    @JsonIgnore
    public InteresseJPASummary getInteresse(){
        return (InteresseJPASummary) interesse;
    }

    public void setInteresse(InteresseJPASummary interesse){
        this.interesse = interesse;
    }

    @OneToOne
    @JoinColumn(name = "id_visita")
    @JsonIgnore
    public VisitaJPASummary getVisita(){
        return (VisitaJPASummary) this.visita;
    }

    public void setVisita(VisitaJPASummary visita){
        this.visita = visita;
    }

}

