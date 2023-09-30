package com.sistema.pedidos.repository;

import com.sistema.pedidos.entity.Category;
import com.sistema.pedidos.entity.Sucursal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BranchOfficeRepository extends JpaRepository<Sucursal, Long>  {


   @Query(value = "SELECT * FROM sucursales s WHERE s.nombre LIKE ?1% ", nativeQuery = true)
    Page <Sucursal> listarSucursalPorNombrePagina(String nombre, Pageable pageable);

    @Query(value = "SELECT * FROM sucursales s WHERE s.estado=true",nativeQuery = true)
    List<Sucursal> listarSucursalActivo();
}
