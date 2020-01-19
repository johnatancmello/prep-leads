package com.prep.api.model.formacao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prep.api.model.modulo.ModuloJPA;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "formacoes")
@Embeddable
public class FormacaoJPASummary extends Formacao{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "formacoes_modulos",
            joinColumns = @JoinColumn(name = "id_formacao"),
            inverseJoinColumns = @JoinColumn(name = "id_modulo"))
    @JsonIgnore
    public List<ModuloJPA> getModulos() {
        return (List<ModuloJPA>) this.modulos;
    }

    public void setModulos(List<ModuloJPA> modulos) {
        this.modulos = modulos;
    }
}