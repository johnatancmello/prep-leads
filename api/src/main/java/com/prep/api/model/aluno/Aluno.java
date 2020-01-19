package com.prep.api.model.aluno;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.prep.api.model.ModelAbstract;
import com.prep.api.model.funcionario.Funcionario;
import com.prep.api.model.pessoa.Pessoa;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Aluno extends ModelAbstract {

    protected Long id;
    protected String status;

    protected Pessoa pessoa;
    protected Funcionario funcionario;

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
}
