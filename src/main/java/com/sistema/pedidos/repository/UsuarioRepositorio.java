package com.sistema.pedidos.repository;

import com.sistema.pedidos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{

	public Optional<Usuario> findByEmail(String email);
	
	public Optional<Usuario> findByUsernameOrEmail(String username,String email);
	
	public Optional<Usuario> findByUsername(String username);
	
	public Boolean existsByUsername(String username);
	
	public Boolean existsByEmail(String email);

	
}
