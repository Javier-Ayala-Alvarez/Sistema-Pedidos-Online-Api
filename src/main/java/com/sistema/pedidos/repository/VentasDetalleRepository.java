package com.sistema.pedidos.repository;

import com.sistema.pedidos.entity.VentaDetalleEntity;
import com.sistema.pedidos.entity.VentaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VentasDetalleRepository extends JpaRepository<VentaDetalleEntity, Long> {
    @Query("SELECT vd FROM VentaDetalleEntity vd LEFT JOIN FETCH vd.product LEFT JOIN FETCH vd.plato WHERE vd.ventaEntity.idVenta = ?1")
    List<VentaDetalleEntity> buscar(Long idVenta);


}
