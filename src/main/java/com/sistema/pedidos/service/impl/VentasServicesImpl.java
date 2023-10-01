package com.sistema.pedidos.service.impl;

import com.sistema.pedidos.DTO.VentasDTO;
import com.sistema.pedidos.DTO.VentasDetalleDTO;
import com.sistema.pedidos.entity.VentaDetalleEntity;
import com.sistema.pedidos.entity.VentaEntity;
import com.sistema.pedidos.repository.VentasRepository;
import com.sistema.pedidos.service.VentasServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Service
public class VentasServicesImpl implements VentasServices {
    @Autowired
     private ModelMapper modelMapper;
    @Autowired
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
        VentasDTO ventaDTO = modelMapper.map(venta, VentasDTO.class);
        return ventaDTO;
    }

    // Convierte de DTO a Entidad
    private VentaEntity mapearEntidad(VentasDTO ventasDTO) {
        VentaEntity venta = modelMapper.map(ventasDTO, VentaEntity.class);

        return venta;
    }
}





