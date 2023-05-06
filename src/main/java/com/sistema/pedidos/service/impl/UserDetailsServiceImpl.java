package com.sistema.pedidos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sistema.pedidos.entity.Usuario;
import com.sistema.pedidos.repository.UsuarioRepository;



@Service
public class UserDetailsServiceImpl  implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usuario usuario = this.usuarioRepository.findByUsername(username);
		if(usuario==null) {
			throw new UsernameNotFoundException("usuario no encontrado");
		}
		return usuario;
	}
	

}
