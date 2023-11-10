package com.sistema.pedidos.service.impl;

import com.sistema.pedidos.DTO.*;
import com.sistema.pedidos.Utileria.EstadoEmpleado;
import com.sistema.pedidos.Utileria.EstadoPedido;
import com.sistema.pedidos.entity.VentaEntity;
import com.sistema.pedidos.repository.VentasRepository;
import com.sistema.pedidos.service.VentasServices;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            ventasDTO.setEstado(EstadoPedido.p.name());
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

            List<Map<String, Object>> resultado = ventasRepository.ventasPorEmpleado(id, EstadoEmpleado.ENTREGANDO.name(), EstadoPedido.d.name());
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
                ventasDTO.setFecha((java.util.Date) row.get("fecha"));
            });

            // generar lista de detalle de ventas
            List<DetalleDeVentaDTO> ventasDetalle = new ArrayList<>();
            List<Map<String, Object>> detalles = this.ventasRepository.detalleVentaPorId(ventasDTO.getId());
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
        try {
            ventasRepository.cambiarEstadoDeVenta(id, estado);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    // agregar comentario a pedido
    public ResponseEntity agregarComentarioPedido(Long id, String comentario) {
        try {
            ventasRepository.agregarComentarioAVenta(id, comentario);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override

   /* public ResponseEntity<List<Object>> listarReporteVentas(String fecha){
        try {
            List<Object> resultado = ventasRepository.listarReporteVentas(fecha);
            // crear instancia de ventasDTO

            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    public ResponseEntity<List<ResportVentaDTO>> listarReporteVentas(String fecha) {
        try {
            List<Object[]> resultado = ventasRepository.listarReporteVentas(fecha);

            // Mapear los resultados a ReporteVentasDTO manualmente
            List<ResportVentaDTO> reporteVentasDTOList = new ArrayList<>();
            for (Object[] objArray : resultado) {
                ResportVentaDTO dto = new ResportVentaDTO();
                dto.setId((BigInteger) objArray[0]);
                dto.setNombre((String) objArray[1]);
                dto.setDireccion((String) objArray[2]);
                dto.setVentas((Double) objArray[3]);
                // Agrega las demás propiedades según las columnas de tu reporte

                reporteVentasDTOList.add(dto);
            }

            return new ResponseEntity<>(reporteVentasDTOList, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // obtener todas las ventas por id de empleado
    public ResponseEntity<Object> obtenerEntregasPorIdEmpleado(Long id) {
        try {
            List<Map<String, Object>> resultado = ventasRepository.todasLasEntregasPorEmpleado(id);
            // lista de ventasDTO
            List<VentasDeliveryDTO> ventasDTOS = new ArrayList<>();

            // recorrer lista resultado
            resultado.forEach((Map<String, Object> row) -> {
                // crear instancia de ventasDTO

                VentasDeliveryDTO venta = new VentasDeliveryDTO();
                venta.setId(Long.parseLong(row.get("id").toString()));
                venta.setNombreEncargado((String) row.get("nombre"));
                venta.setCorreoEncargado((String) row.get("correo"));
                venta.setNumeroTelefono((String) row.get("telefono"));
                venta.setOtrasIndicaciones((String) row.get("indicaciones"));
                venta.setTotal((Double) row.get("total"));
                venta.setAltitud((Double) row.get("latitud"));
                venta.setLongitud((Double) row.get("longitud"));
                venta.setEstado((String) row.get("estado"));
                venta.setFecha((java.util.Date) row.get("fecha"));

                ventasDTOS.add(venta);
            });

            return new ResponseEntity<>(ventasDTOS, HttpStatus.OK);

        } catch (Exception e) {
            // En caso de error, maneja la excepción y devuelve una respuesta de error
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // obtener detalleDePedidoPorIdDeVenta
    public ResponseEntity<Object> detalleDePedidoPorIdDeVenta(Long id) {
        try {
            List<Map<String, Object>> resultado = ventasRepository.detalleVentaPorId(id);
            // lista de ventasDTO
            List<DetalleDeVentaDTO> ventasDTOS = new ArrayList<>();

            // recorrer lista resultado
            resultado.forEach((Map<String, Object> row) -> {
                // crear instancia de ventasDTO

                DetalleDeVentaDTO detalleDTO = new DetalleDeVentaDTO();
                detalleDTO.setId(Long.parseLong(row.get("id").toString()));
                detalleDTO.setImagen((String) row.get("img"));
                detalleDTO.setNombre((String) row.get("nombre"));
                detalleDTO.setPrecio((Double) row.get("precio"));
                detalleDTO.setCantidad((Integer) row.get("cantidad"));
                detalleDTO.setTotal(Double.parseDouble(row.get("total").toString()));
                ventasDTOS.add(detalleDTO);
            });

            return new ResponseEntity<>(ventasDTOS, HttpStatus.OK);

        } catch (Exception e) {
            // En caso de error, maneja la excepción y devuelve una respuesta de error
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Object> obtenerEntregasPorIdEmpleadoConEstado(Long id, String estado) {
        try {
            List<Map<String, Object>> resultado = ventasRepository.ventasPendientesPorSucursalDelEmpleado(id, estado);
            // lista de ventasDTO
            List<VentasDeliveryDTO> ventasDTOS = new ArrayList<>();

            // recorrer lista resultado
            resultado.forEach((Map<String, Object> row) -> {
                // crear instancia de ventasDTO

                VentasDeliveryDTO venta = new VentasDeliveryDTO();
                venta.setId(Long.parseLong(row.get("id").toString()));
                venta.setNombreEncargado((String) row.get("nombre"));
                venta.setCorreoEncargado((String) row.get("correo"));
                venta.setNumeroTelefono((String) row.get("telefono"));
                venta.setOtrasIndicaciones((String) row.get("indicaciones"));
                venta.setTotal((Double) row.get("total"));
                venta.setAltitud((Double) row.get("latitud"));
                venta.setLongitud((Double) row.get("longitud"));
                venta.setEstado((String) row.get("estado"));
                venta.setFecha((java.util.Date) row.get("fecha"));

                // detalle del pedido
                List<Map<String, Object>> detalles = this.ventasRepository.detalleVentaPorId(venta.getId());
                // generar lista de detalle de ventas
                List<DetalleDeVentaDTO> ventasDetalle = new ArrayList<>();
                // recorrer lista de detalle de ventas y asignar valores a ventasDetalleDTO
                detalles.forEach((Map<String, Object> row2) -> {

                            DetalleDeVentaDTO detalleDTO = new DetalleDeVentaDTO();
                            detalleDTO.setId(Long.parseLong(row2.get("id").toString()));
                            detalleDTO.setImagen((String) row2.get("img"));
                            detalleDTO.setNombre((String) row2.get("nombre"));
                            detalleDTO.setPrecio((Double) row2.get("precio"));
                            detalleDTO.setCantidad((Integer) row2.get("cantidad"));
                            detalleDTO.setTotal(Double.parseDouble(row2.get("total").toString()));
                            ventasDetalle.add(detalleDTO);
                        }
                );
                venta.setVentasDetalleDTO(ventasDetalle);
                ventasDTOS.add(venta);
            });

            return new ResponseEntity<>(ventasDTOS, HttpStatus.OK);

        } catch (Exception e) {
            // En caso de error, maneja la excepción y devuelve una respuesta de error
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> asignarEntrega(Long idEmpleado, Long idVenta){
        try {
            ventasRepository.insertarIdVentaIdEmpleado(idEmpleado, idVenta);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}





