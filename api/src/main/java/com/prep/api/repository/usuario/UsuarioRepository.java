package com.prep.api.repository.usuario;

import com.prep.api.model.usuario.UsuarioJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioJPA, Long>{
	Optional<UsuarioJPA> findByIdUsuario(String idUsuario);
}
