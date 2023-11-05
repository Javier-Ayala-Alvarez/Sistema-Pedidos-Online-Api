package com.sistema.pedidos.repository;

import com.sistema.pedidos.entity.VentaDetalleEntity;
import com.sistema.pedidos.entity.VentaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VentasDetalleRepository extends JpaRepository<VentaDetalleEntity, Long> {
    @Query(value = "SELECT * FROM ventas_detalle c WHERE c.venta_id = ?1", nativeQuery = true)
    List<VentaDetalleEntity> buscar(Long id_Venta);
}
