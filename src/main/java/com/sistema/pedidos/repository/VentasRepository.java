package com.sistema.pedidos.repository;

import com.sistema.pedidos.entity.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentasRepository  extends JpaRepository<VentaEntity, Long> {
}
