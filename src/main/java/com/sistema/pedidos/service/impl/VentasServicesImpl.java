package com.sistema.pedidos.service.impl;

import com.sistema.pedidos.DTO.*;
import com.sistema.pedidos.Utileria.EstadoEmpleado;
import com.sistema.pedidos.Utileria.EstadoPedido;
import com.sistema.pedidos.entity.VentaEntity;
import com.sistema.pedidos.repository.VentasRepository;
import com.sistema.pedidos.service.VentasServices;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor

public class VentasServicesImpl implements VentasServices {

    private final ModelMapper modelMapper;
    private final VentasRepository ventasRepository;

    // servicio de productos
    private final ProductServiceImpl productServices;
    // servicio de platos
    private final PlatoServiceImpl platoServices;


    @Override
    public ResponseEntity<VentasDTO> save(VentasDTO ventasDTO) {
        try {
            // Convierte el DTO a la entidad
            ventasDTO.setEstado(EstadoPedido.PENDIENTE.name());
            VentaEntity ventaEntity = mapearEntidad(ventasDTO);
            // Guarda la entidad en la base de datos
            ventaEntity = ventasRepository.save(ventaEntity);
            // Convierte la entidad guardada de nuevo a DTO
            VentasDTO ventaGuardadoDTO = mapearDTO(ventaEntity);
            // Retorna la respuesta con el DTO del detalle de ventas guardado
            return new ResponseEntity<>(ventaGuardadoDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            // En caso de error, maneja la excepción y devuelve una respuesta de error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ResponseEntity<Page<VentasDTO>> listar(long nombre, Pageable pageable) {
        try {
            Page<VentaEntity> ventaEntities = ventasRepository.listarPorNombrePagina(nombre, pageable);
            Page<VentasDTO> ventasDTOS = ventaEntities.map(ventaEntity -> mapearDTO(ventaEntity));
            return new ResponseEntity<>(ventasDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private VentasDTO mapearDTO(VentaEntity venta) {
        return modelMapper.map(venta, VentasDTO.class);
    }

    // Convierte de DTO a Entidad
    private VentaEntity mapearEntidad(VentasDTO ventasDTO) {
        VentaEntity venta = modelMapper.map(ventasDTO, VentaEntity.class);
        return venta;
    }


    // devuelve ventasDetalleDTO por id de empleado
    public ResponseEntity<Object> detalleVentasPorIdEmpleado(Long id) {
        try {

            List<Map<String, Object>> resultado = ventasRepository.ventasPorEmpleado(id, EstadoEmpleado.ENTREGANDO.name());
            // crear instancia de ventasDTO
            VentasDeliveryDTO ventasDTO = new VentasDeliveryDTO();

            // recorrer lista resultado
            resultado.forEach((Map<String, Object> row) -> {
                // asignar valores a ventasDTO
                ventasDTO.setId(Long.parseLong(row.get("id").toString()));
                ventasDTO.setNombreEncargado((String) row.get("nombre"));
                ventasDTO.setCorreoEncargado((String) row.get("correo"));
                ventasDTO.setNumeroTelefono((String) row.get("telefono"));
                ventasDTO.setOtrasIndicaciones((String) row.get("indicaciones"));
                ventasDTO.setTotal((Double) row.get("total"));
                ventasDTO.setAltitud((Double) row.get("latitud"));
                ventasDTO.setLongitud((Double) row.get("longitud"));
                ventasDTO.setEstado((String) row.get("estado"));
            });

            // generar lista de detalle de ventas
            List<DetalleDeVentaDTO> ventasDetalle  = new ArrayList<>();
            List<Map<String, Object>> detalles =  this.ventasRepository.detalleVentaPorId(ventasDTO.getId());
            // recorrer lista de detalle de ventas y asignar valores a ventasDetalleDTO
            detalles.forEach((Map<String, Object> row) -> {
                DetalleDeVentaDTO detalleDTO = new DetalleDeVentaDTO();
                detalleDTO.setId(Long.parseLong(row.get("id").toString()));
                detalleDTO.setImagen((String) row.get("img"));
                detalleDTO.setNombre((String) row.get("nombre"));
                detalleDTO.setPrecio((Double) row.get("precio"));
                detalleDTO.setCantidad((Integer) row.get("cantidad"));
                detalleDTO.setTotal(Double.parseDouble(row.get("total").toString()));

                ventasDetalle.add(detalleDTO);
            });
            ventasDTO.setVentasDetalleDTO(ventasDetalle);



            // retornar ventasDTO
            return new ResponseEntity<>(ventasDTO, HttpStatus.OK);

        } catch (Exception e) {
            // En caso de error, maneja la excepción y devuelve una respuesta de error
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // historico de ventas entregadas por id de empleado


    // cambiar estado del pedido
    public ResponseEntity<Object> cambiarEstadoPedido(Long id, String estado) {
       if (ventasRepository.cambiarEstadoDeVenta(id, estado) == 1) {
            return new ResponseEntity<>("Pedido :" + estado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se pudo actualizar el estado del pedido", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    // agregar comentario a pedido
    public ResponseEntity agregarComentarioPedido(Long id, String comentario) {
        if (ventasRepository.agregarComentarioAVenta(id, comentario) == 1) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


}





