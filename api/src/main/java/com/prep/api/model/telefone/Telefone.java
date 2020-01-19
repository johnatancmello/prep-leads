package com.prep.api.model.telefone;

import com.prep.api.model.ModelAbstract;
import com.prep.api.model.pessoa.Pessoa;

public class Telefone extends ModelAbstract {

    protected Long id;
    protected String telefone, tipo;
    protected Pessoa pessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
