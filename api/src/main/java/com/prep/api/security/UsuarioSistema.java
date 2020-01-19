package com.prep.api.security;

import com.prep.api.model.funcionario.FuncionarioJPA;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UsuarioSistema extends User{
	private static final long serialVersionUID = 1L;
	
	private FuncionarioJPA funcionario;

	public UsuarioSistema(FuncionarioJPA funcionario, Collection<? extends GrantedAuthority> authorities) {
		super(funcionario.getUsuario().getIdUsuario(),
				funcionario.getUsuario().getSenha(), authorities);
		this.funcionario = funcionario;
	}

	public FuncionarioJPA getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioJPA funcionario) {
		this.funcionario = funcionario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

