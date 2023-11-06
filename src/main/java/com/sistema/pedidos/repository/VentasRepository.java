package com.sistema.pedidos.repository;

import com.sistema.pedidos.Utileria.EstadoEmpleado;
import com.sistema.pedidos.entity.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VentasRepository extends JpaRepository<VentaEntity, Long> {


    @Query(value = "select v.id_venta           as id,\n" +
            "       v.nombre_encargado   as nombre,\n" +
            "       v.correo_encargado   as correo,\n" +
            "       v.numero_telefono    as telefono,\n" +
            "       v.otras_indicaciones as indicaciones,\n" +
            "       v.total,\n" +
            "       v.altitud            as latitud,\n" +
            "       v.longitud,\n" +
            "       v.estado\n" +
            "from empleado e\n" +
            "         inner join public.venta_empleado ve on e.id_empleado = ve.empleado_id\n" +
            "         inner join ventas v on ve.venta_id = v.id_venta\n" +
            "where e.usuario_id = :id \n" +
            "  and e.estado_empleado = :estado ;", nativeQuery = true)
    List<Map<String, Object>> ventasPorEmpleado(@Param("id") Long id, @Param("estado") String estado);


    @Query(value = "\n" +
            "select v.id_venta           as id,\n" +
            "       v.nombre_encargado   as nombre,\n" +
            "       v.correo_encargado   as correo,\n" +
            "       v.numero_telefono    as telefono,\n" +
            "       v.otras_indicaciones as indicaciones,\n" +
            "       v.total,\n" +
            "       v.altitud            as latitud,\n" +
            "       v.longitud,\n" +
            "       v.estado\n" +
            "from empleado e\n" +
            "         inner join public.venta_empleado ve on e.id_empleado = ve.empleado_id\n" +
            "         inner join ventas v on ve.venta_id = v.id_venta\n" +
            "where e.usuario_id = :id \n", nativeQuery = true)
    List<Map<String, Object>> todasLasEntregasPorEmpleado(@Param("id") Long id);


    // ventas con estado pendiente
    @Query(value = "select v.id_venta           as id,\n" +
            "       v.nombre_encargado   as nombre,\n" +
            "       v.correo_encargado   as correo,\n" +
            "       v.numero_telefono    as telefono,\n" +
            "       v.otras_indicaciones as indicaciones,\n" +
            "       v.total,\n" +
            "       v.altitud            as latitud,\n" +
            "       v.longitud,\n" +
            "       v.estado\n" +
            "from ventas v  where v.sucursal_id = (select e.sucursal_id from empleado e where e.usuario_id = :id ) and v.estado = :estado ;" , nativeQuery = true)
    List<Map<String, Object>> ventasPendientesPorSucursalDelEmpleado(@Param("id") Long id, @Param("estado") String estado);


    // cambiar estado de ventas
    @Query(value = "update ventas set estado = :estado where id_venta = :id ;" , nativeQuery = true)
    int cambiarEstadoDeVenta(@Param("id") Long id, @Param("estado") String estado);

    // agregar comentario a ventas
    @Query(value = "update ventas set comentario_entrega = :comentario where id_venta = :id ;" , nativeQuery = true)
    int agregarComentarioAVenta(@Param("id") Long id, @Param("comentario") String comentario);


}
