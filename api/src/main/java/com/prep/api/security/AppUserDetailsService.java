package com.prep.api.security;

import com.prep.api.model.funcionario.FuncionarioJPA;
import com.prep.api.model.usuario.UsuarioJPA;
import com.prep.api.repository.funcionario.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws AuthenticationServiceException {
        FuncionarioJPA funcionario = funcionarioRepository.findByIdUsuario(username);
        if (funcionario == null){
            throw new AuthenticationServiceException("Usuário não encontrado!");
        }
        return new UsuarioSistema(funcionario, getPermissoes(funcionario.getUsuario()));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(UsuarioJPA usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		usuario.getPermissoes()
				.forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase())));
		return authorities;
	}

}
