package com.sistema.pedidos.repository;

import com.sistema.pedidos.DTO.PlatoDTO;
import com.sistema.pedidos.entity.Plato;
import org.hibernate.sql.Select;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface PlatoRepository extends JpaRepository<Plato, Long> {


    @Query("select p from Plato p where p.estado = ?1")
    Page<Plato> getAllByEstadoEquals(Boolean estato, Pageable pageable);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Plato p set p.estado = ?1 where p.id = ?2")
    int updateEstado(Boolean estado, Long id);

}
