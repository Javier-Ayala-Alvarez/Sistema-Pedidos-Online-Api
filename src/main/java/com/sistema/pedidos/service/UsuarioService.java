package com.sistema.pedidos.service;

import java.util.Set;

import com.sistema.pedidos.entity.Usuario;
import com.sistema.pedidos.entity.UsuarioRol;

public interface UsuarioService {
	
	public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

	public Usuario obtenerUsuario(String username);
	
	public void eliminarUsuario(Long usuarioId);
}
