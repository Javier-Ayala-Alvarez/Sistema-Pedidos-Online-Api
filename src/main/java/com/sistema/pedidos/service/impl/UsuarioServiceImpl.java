package com.sistema.pedidos.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.pedidos.entity.Usuario;
import com.sistema.pedidos.entity.UsuarioRol;
import com.sistema.pedidos.repository.RolRepository;
import com.sistema.pedidos.repository.UsuarioRepository;
import com.sistema.pedidos.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RolRepository rolRepository;

	@Override
	public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
		// TODO Auto-generated method stub
		Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
		if(usuarioLocal!=null) {
			System.out.println("el usuario ya existe");
			throw new Exception ("el usario ya existe");
		}else {
			for(UsuarioRol usuarioRol:usuarioRoles) {
				rolRepository.save(usuarioRol.getRol());
			}
			usuario.getUsuarioRoles().addAll(usuarioRoles);
			usuarioLocal=usuarioRepository.save(usuario);
			}
		return usuarioLocal;
	}

	@Override
	public Usuario obtenerUsuario(String username) {
		// TODO Auto-generated method stub
		return usuarioRepository.findByUsername(username);
	}

	@Override
	public void eliminarUsuario(Long usuarioId) {
		// TODO Auto-generated method stub
		usuarioRepository.deleteById(usuarioId);
	}





}
