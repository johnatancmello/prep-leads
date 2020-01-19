package com.prep.api.model.contato;

import com.prep.api.model.ModelAbstract;
import com.prep.api.model.funcionario.Funcionario;
import com.prep.api.model.interesse.Interesse;
import com.prep.api.model.pessoa.Pessoa;
import com.prep.api.model.visita.Visita;

import java.util.Date;

public class Contato extends ModelAbstract {

    protected Long id;
    protected String status, observacao;
    protected Date dataDoContato;
    protected Visita visita;

    protected Pessoa pessoa;
    protected Funcionario funcionario;
    protected Interesse interesse;

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

    public Date getDataDoContato() {
        return dataDoContato;
    }

    public void setDataDoContato(Date dataDoContato) {
        this.dataDoContato = dataDoContato;
    }
}
