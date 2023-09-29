package com.sistema.pedidos.repository;

import com.sistema.pedidos.entity.Category;
import com.sistema.pedidos.entity.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

     Page<Empleado> findByEstadoEquals(Pageable pageable, Boolean estado);

     @Transactional
     @Modifying(clearAutomatically = true)
     @Query("UPDATE Empleado e SET e.estado = false WHERE e.id = ?1")
     void darDeBajaEmpleado(Integer id);

     @Query(value = "SELECT * FROM empleado e WHERE  e.nombre LIKE ?1%",nativeQuery = true)
     Page<Empleado> listarEmpleadoPorNombrePagina(String CT_Nombre, Pageable pageable);


}
