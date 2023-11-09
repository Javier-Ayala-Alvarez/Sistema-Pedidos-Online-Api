package com.sistema.pedidos.repository;

import com.sistema.pedidos.DTO.VentasDeliveryDTO;
import com.sistema.pedidos.Utileria.EstadoEmpleado;
import com.sistema.pedidos.entity.VentaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
public interface VentasRepository extends JpaRepository<VentaEntity, Long> {
    @Query(value = "SELECT * FROM ventas c WHERE c.usuario_id = ?1", nativeQuery = true)
    Page<VentaEntity> listarPorNombrePagina(Long id_Usuario, Pageable pageable);


    @Query(value = "select v.id_venta           as id,\n" +
            "       v.nombre_encargado   as nombre,\n" +
            "       v.correo_encargado   as correo,\n" +
            "       v.numero_telefono    as telefono,\n" +
            "       v.otras_indicaciones as indicaciones,\n" +
            "       v.total,\n" +
            "       v.altitud            as latitud,\n" +
            "       v.longitud,\n" +
            "       v.estado, v.fecha \n" +
            "from empleado e\n" +
            "         inner join public.venta_empleado ve on e.id_empleado = ve.empleado_id\n" +
            "         inner join ventas v on ve.venta_id = v.id_venta\n" +
            "where e.usuario_id = :id \n" +
            "  and e.estado_empleado = :estado and v.estado =:vestado  ", nativeQuery = true)
    List<Map<String, Object>> ventasPorEmpleado(@Param("id") Long id, @Param("estado") String estado, @Param("vestado") String ventasEstado);


    @Query(value = "\n" +
            "select v.id_venta           as id,\n" +
            "       v.nombre_encargado   as nombre,\n" +
            "       v.correo_encargado   as correo,\n" +
            "       v.numero_telefono    as telefono,\n" +
            "       v.otras_indicaciones as indicaciones,\n" +
            "       v.total,\n" +
            "       v.altitud            as latitud,\n" +
            "       v.longitud,\n" +
            "       v.estado , v.fecha\n" +
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
            "       v.estado , v.fecha \n" +
            "from ventas v  where v.sucursal_id = (select e.sucursal_id from empleado e where e.usuario_id =:id ) and v.estado = :estado ;", nativeQuery = true)
    List<Map<String, Object>> ventasPendientesPorSucursalDelEmpleado(@Param("id") Long id, @Param("estado") String estado);


    // cambiar estado de ventas
    @Transactional
    @Modifying
    @Query(value = "update ventas set estado = :estado where id_venta = :id ;", nativeQuery = true)
    void cambiarEstadoDeVenta(@Param("id") Long id, @Param("estado") String estado);

    // agregar comentario a ventas
    @Transactional
    @Modifying
    @Query(value = "update ventas set comentario_entrega = :comentario where id_venta = :id ;", nativeQuery = true)
    void agregarComentarioAVenta(@Param("id") Long id, @Param("comentario") String comentario);

    //obtener detalle de ventas por id de ventas
    @Query(value = "\n" +
            "SELECT vd.id_venta_detalle as id,\n" +
            "    CASE\n" +
            "        WHEN vd.producto_id IS NOT NULL THEN p.url_imagen\n" +
            "        ELSE pm.url_imagen\n" +
            "        END AS img,\n" +
            "    CASE\n" +
            "        WHEN vd.producto_id IS NOT NULL THEN p.nombre\n" +
            "        ELSE pm.nombre\n" +
            "        END AS nombre,\n" +
            "    CASE\n" +
            "        WHEN vd.producto_id IS NOT NULL THEN p.precio_venta\n" +
            "        ELSE pm.precio_total\n" +
            "        END AS precio,\n" +
            "    vd.cantidad,\n" +
            "    vd.precio_total as total\n" +
            "FROM ventas_detalle vd\n" +
            "         LEFT JOIN public.producto p ON p.id_producto = vd.producto_id\n" +
            "         LEFT JOIN public.plato_menu pm ON pm.id = vd.plato_id\n" +
            "WHERE vd.venta_id = :id ;", nativeQuery = true)
    List<Map<String, Object>> detalleVentaPorId(@Param("id") Long id);


}
