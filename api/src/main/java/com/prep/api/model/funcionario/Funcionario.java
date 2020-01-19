package com.prep.api.model.funcionario;

import com.prep.api.model.ModelAbstract;
import com.prep.api.model.meta.Meta;
import com.prep.api.model.pessoa.Pessoa;
import com.prep.api.model.setor.Setor;
import com.prep.api.model.usuario.Usuario;

import java.util.Set;

public class Funcionario extends ModelAbstract {

    protected Long id;
    protected String status;
    protected Integer cargaHoraria;

    protected Pessoa pessoa;
    protected Usuario usuario;
    protected Setor setor;
    protected Set<? extends Meta> metas;

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

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }


}
