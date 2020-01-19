package com.prep.api.model.meta;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.prep.api.model.funcionario.FuncionarioJPA;
import com.prep.api.model.setor.SetorJPA;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "metas")
@Embeddable
public class MetaJPA extends Meta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
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

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "metas")
    @JsonIgnoreProperties("metas")
    public Set<FuncionarioJPA> getFuncionarios() {
        return (Set<FuncionarioJPA>) funcionarios;
    }

    public void setFuncionarios(Set<FuncionarioJPA> funcionarios) {
        this.funcionarios = funcionarios;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "setores_metas",
            joinColumns = @JoinColumn(name = "id_setor"),
            inverseJoinColumns = @JoinColumn(name = "id_meta"))
    public Set<SetorJPA> getSetores() {
        return (Set<SetorJPA>) setores;
    }

    public void setSetores(Set<SetorJPA> setores) {
        this.setores = setores;
    }
}
