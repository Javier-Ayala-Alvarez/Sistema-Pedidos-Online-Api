package com.sistema.pedidos.repository;

import com.sistema.pedidos.DTO.PlatoDTO;
import com.sistema.pedidos.entity.Plato;
import org.hibernate.sql.Select;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface PlatoRepository extends JpaRepository<Plato, Long> {


    @Query("select p from Plato p where p.estado = ?1")
    Page<Plato> getAllByEstadoEquals(Boolean estato, Pageable pageable);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Plato p set p.estado = ?1 where p.id = ?2")
    int updateEstado(Boolean estado, Long id);

    //delete relation on plato_producto

    @Transactional
    @Modifying
    @Query(value = "delete from PlatoProducto p where p.id = ?1")
     void deletePlatoProducto(Long id) ;


    // Query get plato by relation ventasDetalle.platoid
    @Query("select p from Plato p inner join VentaDetalleEntity v on p.id = v.plato.id where v.ventaEntity.idVenta = :id")
    List<Plato> getPlatosByVentasDetalle(Long id);


}
