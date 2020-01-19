package com.prep.api.model.visita;

import com.prep.api.model.ModelAbstract;
import com.prep.api.model.contato.Contato;
import com.prep.api.model.funcionario.Funcionario;
import com.prep.api.model.pessoa.Pessoa;

import java.util.Date;

public class Visita extends ModelAbstract {

    protected Long id;
    protected String status, observacao;
    protected Date dataDoAgendamento;
    protected Pessoa pessoa;
    protected Funcionario funcionario;
    protected Contato contato;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
