package com.sistema.pedidos.repository;

import com.sistema.pedidos.entity.VentaDetalleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentasDetalleRepository extends JpaRepository<VentaDetalleEntity, Long> {
}
