package com.prep.api.model.formacao;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.prep.api.model.area.AreaJPA;
import com.prep.api.model.modulo.ModuloJPA;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "formacoes")
@Embeddable
public class FormacaoJPA extends Formacao{

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

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    @NotNull
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
    public List<ModuloJPA> getModulos() {
        return (List<ModuloJPA>) this.modulos;
    }

    public void setModulos(List<ModuloJPA> modulos) {
        this.modulos = modulos;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_area")
    public AreaJPA getArea() {
        return (AreaJPA) area;
    }

    public void setArea(AreaJPA area) {
        this.area = area;
    }
}
