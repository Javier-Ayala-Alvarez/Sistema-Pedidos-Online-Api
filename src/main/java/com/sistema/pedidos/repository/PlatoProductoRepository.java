package com.sistema.pedidos.repository;

import com.sistema.pedidos.entity.PlatoProducto;
import org.springframework.data.repository.CrudRepository;

public interface PlatoProductoRepository extends CrudRepository<PlatoProducto,Long> {
    int deleteByPlatoId(Long id);
}
