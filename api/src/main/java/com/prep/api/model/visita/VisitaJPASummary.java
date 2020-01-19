package com.prep.api.model.visita;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prep.api.model.contato.ContatoJPA;
import com.prep.api.model.contato.ContatoJPASummary;
import com.prep.api.model.funcionario.FuncionarioJPASummary;
import com.prep.api.model.pessoa.PessoaJPA;
import com.prep.api.model.pessoa.PessoaJPASummary;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "visitas")
@Embeddable
public class VisitaJPASummary extends Visita {

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

    public Date getDataDoAgendamento() {
        return dataDoAgendamento;
    }

    public void setDataDoAgendamento(Date dataDoAgendamento) {
        this.dataDoAgendamento = dataDoAgendamento;
    }

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
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
    @JoinColumn(name = "id_contato")
    public ContatoJPA getContato(){
        return (ContatoJPA) this.contato;
    }

    public void setContato(ContatoJPA contato){
        this.contato = contato;
    }
}
