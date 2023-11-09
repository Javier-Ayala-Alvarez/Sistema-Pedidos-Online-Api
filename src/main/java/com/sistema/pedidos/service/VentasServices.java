package com.sistema.pedidos.service;

import com.sistema.pedidos.DTO.VentasDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface VentasServices {
    public ResponseEntity<VentasDTO> save(VentasDTO ventasDTO);

    ResponseEntity<Object> detalleVentasPorIdEmpleado(Long id);

    public ResponseEntity<Page<VentasDTO>> listar(long nombre, Pageable pageable);

    ResponseEntity agregarComentarioPedido(Long id, String comentario);
    ResponseEntity<Object> cambiarEstadoPedido(Long id, String estado);

    public ResponseEntity<Object> obtenerEntregasPorIdEmpleado(Long id);

    public ResponseEntity<Object> detalleDePedidoPorIdDeVenta(Long id);

}
