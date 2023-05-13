package com.sistema.pedidos.repository;
import com.sistema.pedidos.entity.Rol;
import com.sistema.pedidos.entity.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RolRepositorio extends JpaRepository<Rol, Long>{
    @Query(value = "SELECT r FROM Rol r where r.nombre = ?1")
	public Optional<Rol> findByNombre(String nombre);
	
}
