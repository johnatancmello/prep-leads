package com.prep.api.model.formacao;

import com.prep.api.model.ModelAbstract;
import com.prep.api.model.area.Area;
import com.prep.api.model.modulo.Modulo;

import java.util.List;

public class Formacao extends ModelAbstract {

    protected Long id;
    protected String nome, cargaHoraria, status;
    protected Area area;

    protected List<? extends Modulo> modulos;

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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
