package com.prep.api.repository.funcionario;

import com.prep.api.model.funcionario.FuncionarioJPA;
import com.prep.api.repository.funcionario.impl.query.FuncionarioRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface FuncionarioRepository
		extends JpaRepository<FuncionarioJPA, Long>,
		FuncionarioRepositoryQuery {
	Optional<FuncionarioJPA> findByUsuarioIdUsuario(String usuario);
}
