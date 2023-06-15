package com.sistema.pedidos.repository;

import com.sistema.pedidos.entity.ClientesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ClienteRepository extends JpaRepository<ClientesEntity, Integer>{
    @Query(value = "select c from ClientesEntity c where c.estado = true and c.nombre like ?1% ")
    Page<ClientesEntity> listarClientesPorNombre(String CT_Nombre, Pageable pageable);
    @Transactional
    @Modifying
    @Query(value = "update ClientesEntity c  set c.estado = false where c.idCliente = ?1")
    int darBajaCliente(Integer id);

    @Query(value = "select c from ClientesEntity c where c.usuario.id = ?1")
    ClientesEntity buscarClientePorIdUsuario(Long idUsuario);

}
