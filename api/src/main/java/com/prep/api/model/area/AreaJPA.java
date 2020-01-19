package com.prep.api.model.area;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.prep.api.model.formacao.FormacaoJPA;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "areas")
@Embeddable
public class AreaJPA extends Area {

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

    @JoinColumn(name = "id_area_dependente")
    @OneToOne(cascade = CascadeType.ALL)
    public AreaJPA getAreaPrecedente() {
        return (AreaJPA) areaPrecedente;
    }

    public void setAreaPrecedente(AreaJPA areaPrecedente) {
        this.areaPrecedente = areaPrecedente;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "area")
    @JsonIgnoreProperties("area")
    public List<FormacaoJPA> getFormacoes() {
        return (List<FormacaoJPA>) formacoes;
    }

    public void setFormacoes(List<FormacaoJPA> fomacoes) {
        this.formacoes = fomacoes;
    }
}
