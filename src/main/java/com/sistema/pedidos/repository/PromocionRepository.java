package com.sistema.pedidos.repository;

import com.sistema.pedidos.entity.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PromocionRepository extends JpaRepository<Promocion, Long> {
    @Query("select p from Promocion p where p.estado = 1")
    public List<Promocion> findAllByEstado();
}
