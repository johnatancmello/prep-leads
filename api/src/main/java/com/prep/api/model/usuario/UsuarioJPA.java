package com.prep.api.model.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.prep.api.model.permissao.PermissaoJPA;
import com.prep.api.security.BCryptPasswordDeserializer;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Embeddable
@DynamicUpdate
public class UsuarioJPA extends Usuario {

	@Id
	@NotBlank
	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	@JsonIgnore
	public String getSenha() {
		return senha;
	}

	@JsonDeserialize(using = BCryptPasswordDeserializer.class )
	@JsonProperty
	public void setSenha(String senha) {
		this.senha = senha;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuarios_permissoes",
			joinColumns = @JoinColumn(name = "id_usuario"),
			inverseJoinColumns = @JoinColumn(name = "id_permissao"))
	@JsonIgnoreProperties("usuarios")
	public Set<PermissaoJPA> getPermissoes() {
		return (Set<PermissaoJPA>) permissoes;
	}

	public void setPermissoes(Set<PermissaoJPA> permissoes) {
		this.permissoes = permissoes;
	}
}
