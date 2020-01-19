package com.prep.api.model.setor;

import com.prep.api.model.ModelAbstract;

public class Setor extends ModelAbstract {

    protected Long id;
    protected String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
