package com.sistema.pedidos.repository;

import com.sistema.pedidos.entity.PuestoLaboralEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PuestoRepository extends JpaRepository<PuestoLaboralEntity, Integer> {
    @Query("SELECT p FROM PuestoLaboralEntity p where p.estado = true")
    public Page<PuestoLaboralEntity> findAll(Pageable page);

    @Query("SELECT p FROM PuestoLaboralEntity p where p.estado = true and p.id = ?1")
    public Optional<PuestoLaboralEntity> findById(Integer id);
}
