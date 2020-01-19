package com.prep.api.model.contato;

import com.fasterxml.jackson.annotation.*;
import com.prep.api.model.funcionario.FuncionarioJPASummary;
import com.prep.api.model.interesse.InteresseJPA;
import com.prep.api.model.interesse.InteresseJPASummary;
import com.prep.api.model.pessoa.PessoaJPA;
import com.prep.api.model.pessoa.PessoaJPASummary;
import com.prep.api.model.visita.VisitaJPA;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "contatos")
@Embeddable
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class ContatoJPA extends Contato {

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
    public PessoaJPA getPessoa() {
        return (PessoaJPA) pessoa;
    }

    public void setPessoa(PessoaJPA pessoa) {
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
    public InteresseJPA getInteresse(){
        return (InteresseJPA) interesse;
    }

    public void setInteresse(InteresseJPA interesse){
        this.interesse = interesse;
    }

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_visita")
    public VisitaJPA getVisita(){
        return (VisitaJPA) this.visita;
    }


    public void setVisita(VisitaJPA visita){
        this.visita = visita;
    }
}
