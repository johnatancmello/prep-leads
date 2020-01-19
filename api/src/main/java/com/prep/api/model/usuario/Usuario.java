package com.prep.api.model.usuario;

import com.prep.api.model.ModelAbstract;
import com.prep.api.model.permissao.Permissao;

import java.util.Set;

public class Usuario extends ModelAbstract {

    protected String idUsuario;
    protected String senha;

    protected Set<? extends Permissao> permissoes;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
