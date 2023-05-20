package com.sistema.pedidos.repository;

import com.sistema.pedidos.entity.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRolesRepository extends JpaRepository<UsuarioRol, Long> {
 @Query("select ru from UsuarioRol ru where ru.usuario.id = ?1")
    public UsuarioRol buscarRolUsuario(Long idUsuario);
}
