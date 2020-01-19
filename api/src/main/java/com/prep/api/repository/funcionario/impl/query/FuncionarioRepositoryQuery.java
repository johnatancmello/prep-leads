package com.prep.api.repository.funcionario.impl.query;

import com.prep.api.model.funcionario.Funcionario;
import com.prep.api.model.funcionario.FuncionarioFilter;
import com.prep.api.model.funcionario.FuncionarioJPA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FuncionarioRepositoryQuery {
    public Page<Funcionario> filter(FuncionarioFilter filter, Pageable pageable);
    public Long getTotal(FuncionarioFilter filter);
    public FuncionarioJPA findByIdUsuario(String usuario);

}
