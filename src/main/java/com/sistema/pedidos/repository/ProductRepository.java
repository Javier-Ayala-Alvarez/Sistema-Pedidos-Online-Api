package com.sistema.pedidos.repository;

import com.sistema.pedidos.DTO.ProductoPlatoDTO;
import com.sistema.pedidos.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductRepository extends JpaRepository <Product, Long>{

    @Query(value = "select p from Product p where p.estado = true")
    List<Product> listarProductosActivos();



    @Transactional
    @Query(value = "SELECT a.idProducto,a.nombre,a.descripcion,a.estado,a.precioVenta,a.ganancia,a.url_imagen,a.evento,a.promocion,a.idCombo,a.nombreProducto,a.urlImagenProducto, a.categoria_id  FROM (" +
            " SELECT 0 AS idProducto, p.nombre, p.descripcion, p.estado, p.precio_Total AS precioVenta," +
            " NULL AS ganancia, p.url_imagen, NULL AS evento, p.promocion_id AS promocion," +
            " p.id AS idCombo, r.nombre AS nombreProducto, r.url_imagen AS urlImagenProducto, p.categoria_id" +
            " FROM plato_menu p" +
            " INNER JOIN plato_producto m ON m.plato_id = p.id" +
            " INNER JOIN producto r ON r.id_producto = m.producto_id" +
            " UNION ALL" +
            " SELECT r.id_producto AS idProducto, r.nombre AS nombreProducto, r.descripcion, r.estado, r.precio_venta AS precioVenta," +
            " r.ganancia, r.url_imagen, r.evento_id_evento, r.promocion_id_promocion AS promocion," +
            " 0 AS idCombo, NULL AS nombreProducto, NULL AS urlImagenProducto, r.category_id" +
            " FROM producto r " +
            " ) a", nativeQuery = true)
    List<Map<String, Object>> listarProductPorPagina();

    Optional<Product> findById(Long id);

    @Query(value = "SELECT * FROM producto p WHERE p.nombre LIKE ?1%",nativeQuery = true)
    Page<Product> listarProductPorNombrePagina(String nombre, Pageable pageable);
    @Query(value = "SELECT p.* FROM producto p inner join plato_producto pp on pp.producto_id = p.id_producto where pp.plato_id = ?1 ",nativeQuery = true)
    List<Product> listarProductPorCombo(Long id);
}
