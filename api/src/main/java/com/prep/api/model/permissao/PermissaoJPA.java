package com.prep.api.model.permissao;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.prep.api.model.usuario.UsuarioJPA;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "permissoes")
@Embeddable
public class PermissaoJPA extends Permissao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "permissoes")
	public Set<UsuarioJPA> getUsuarios() {
		return (Set<UsuarioJPA>) usuarios;
	}

	public void setUsuarios(Set<UsuarioJPA> usuarios) {
		this.usuarios = usuarios;
	}
}
