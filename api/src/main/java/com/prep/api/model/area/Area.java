package com.prep.api.model.area;

import com.prep.api.model.ModelAbstract;
import com.prep.api.model.formacao.Formacao;

import java.util.List;
import java.util.Set;

public class Area extends ModelAbstract {

    protected Long id;
    protected String nome;
    protected Area areaPrecedente;
    protected List<? extends Formacao> formacoes;

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

    public Area getAreaPrecedente() {
        return areaPrecedente;
    }

    public void setAreaPrecedente(Area areaPrecedente) {
        this.areaPrecedente = areaPrecedente;
    }
}
