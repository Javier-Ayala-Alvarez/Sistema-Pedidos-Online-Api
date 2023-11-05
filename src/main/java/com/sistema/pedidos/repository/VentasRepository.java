package com.sistema.pedidos.repository;

import com.sistema.pedidos.entity.VentaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VentasRepository  extends JpaRepository<VentaEntity, Long> {
    @Query(value = "SELECT * FROM ventas c WHERE c.usuario_id = ?1", nativeQuery = true)
    Page<VentaEntity> listarPorNombrePagina(Long id_Usuario, Pageable pageable);

}
