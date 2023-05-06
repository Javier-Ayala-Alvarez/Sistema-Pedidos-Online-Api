package com.sistema.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pedidos.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);

}
