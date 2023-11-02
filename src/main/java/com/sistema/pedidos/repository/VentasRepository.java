package com.sistema.pedidos.repository;

import com.sistema.pedidos.entity.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VentasRepository  extends JpaRepository<VentaEntity, Long> {


    @Query(value = "select v.id_venta           as id,\n" +
            "       v.nombre_encargado   as nombre,\n" +
            "       v.correo_encargado   as correo,\n" +
            "       v.numero_telefono    as telefono,\n" +
            "       v.otras_indicaciones as indicaciones,\n" +
            "       v.total,\n" +
            "       v.altitud            as latitud,\n" +
            "       v.longitud,\n" +
            "       v.estado\n" +
            "from ventas v\n" +
            "         inner join venta_empleado ve on ve.venta_id = v.id_venta\n" +
            "where ve.empleado_id = 1 and (select e.estado_empleado from empleado e where e.id_empleado = 1) = 'ENTREGANDO'; ", nativeQuery = true)
    List<Map<String, Object>> ventasPorEmpleado(Long id);


}
