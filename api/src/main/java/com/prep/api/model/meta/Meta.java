package com.prep.api.model.meta;

import com.prep.api.model.ModelAbstract;
import com.prep.api.model.funcionario.Funcionario;
import com.prep.api.model.setor.SetorJPA;

import java.util.Set;

public class Meta extends ModelAbstract {

    protected Long id;
    protected String nome, descricao;
    protected Integer periodo;
    protected Float valor;

    protected Set<? extends Funcionario> funcionarios;
    protected Set<? extends SetorJPA> setores;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
}
