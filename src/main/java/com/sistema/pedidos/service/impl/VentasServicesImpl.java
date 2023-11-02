package com.sistema.pedidos.service.impl;

import com.sistema.pedidos.DTO.PlatoDTO;
import com.sistema.pedidos.DTO.ProductDTO;
import com.sistema.pedidos.DTO.VentasDTO;
import com.sistema.pedidos.DTO.VentasDeliveryDTO;
import com.sistema.pedidos.entity.VentaEntity;
import com.sistema.pedidos.repository.PlatoRepository;
import com.sistema.pedidos.repository.ProductRepository;
import com.sistema.pedidos.repository.VentasRepository;
import com.sistema.pedidos.service.VentasServices;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor

public class VentasServicesImpl implements VentasServices {

    private ModelMapper modelMapper;
    private ProductRepository productRepository;
    private PlatoRepository platoRepository;
    private VentasRepository ventasRepository;


    @Override
    public ResponseEntity<VentasDTO> save(VentasDTO ventasDTO) {
        try {
            // Convierte el DTO a la entidad
            ventasDTO.setEstado("p");
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

    private VentasDTO mapearDTO(VentaEntity venta) {
        return modelMapper.map(venta, VentasDTO.class);
    }

    // Convierte de DTO a Entidad
    private VentaEntity mapearEntidad(VentasDTO ventasDTO) {

        return modelMapper.map(ventasDTO, VentaEntity.class);
    }

    // devuelve ventasDetalleDTO por id de empleado
    public ResponseEntity<Object> detalleVentasPorIdEmpleado(Long id) {
        try {

            List<Map<String, Object>> resultado = ventasRepository.ventasPorEmpleado(id);
            // crear instancia de ventasDTO
            VentasDeliveryDTO ventasDTO = new VentasDeliveryDTO();

            // recorrer lista resultado
            resultado.forEach((Map<String, Object> row) -> {
                // asignar valores a ventasDTO

                ventasDTO.setTotal((Double) row.get("total"));
                ventasDTO.setEstado((String) row.get("estado"));

            });

            //resultado.forEach();

            // Crear instancias de productosDTO y platosDTO
            ProductDTO productoDTO = new ProductDTO();
            PlatoDTO platoDTO = new PlatoDTO();

            //asignar productosDTO y platosDTO a ventasDTO
            ventasDTO.setProducto(productoDTO);
            ventasDTO.setPlato(platoDTO);

        } catch (Exception e) {
            // En caso de error, maneja la excepción y devuelve una respuesta de error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return null;
    }


}





