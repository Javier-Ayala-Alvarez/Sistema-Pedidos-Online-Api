package com.sistema.pedidos.repository;

import com.sistema.pedidos.DTO.EmpleadoDTO;
import com.sistema.pedidos.entity.Category;
import com.sistema.pedidos.entity.Empleado;
import com.sistema.pedidos.entity.Sucursal;
import org.hibernate.sql.Select;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    Page<Empleado> findByEstadoEquals(Pageable pageable, Boolean estado);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Empleado e SET e.estado = false WHERE e.id = ?1")
    void darDeBajaEmpleado(Integer id);

    @Query(value = "SELECT * FROM empleado e WHERE  e.nombre LIKE ?1%", nativeQuery = true)
    Page<Empleado> listarEmpleadoPorNombrePagina(String CT_Nombre, Pageable pageable);

    @Query(value = "SELECT * FROM empleado e WHERE  e.nombre LIKE ?1%", nativeQuery = true)
    Page<Empleado> listarEmpleadoPorNombrePagina2(String CT_Nombre, Pageable pageable);


    // obtener estado de empleado por id
    @Query(value = "select e.estado_empleado from empleado e where e.usuario_id = :id ", nativeQuery = true)
    String getEstadoEmpleadoByIdUsuario(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE empleado set estado_empleado = :estado  where usuario_id = :id ;", nativeQuery = true)
    int updateEstadoEmpleado(@Param("estado") String estado, @Param("id") Long id);

   // obtener empleados con estado entregando
   @Query("Select e from Empleado e where e.estadoEmpleado = :estado")
   List<Empleado> empleadosConEstadoEntrega( @Param("estado") String estado);





}
