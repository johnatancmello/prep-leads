package com.prep.api.model.permissao;

import com.prep.api.model.ModelAbstract;
import com.prep.api.model.usuario.Usuario;

import java.util.Set;

public class Permissao extends ModelAbstract {
    protected Long id;
    protected Set<? extends Usuario> usuarios;
    protected String descricao;
}
