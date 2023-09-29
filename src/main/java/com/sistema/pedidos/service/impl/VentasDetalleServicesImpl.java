package com.sistema.pedidos.service.impl;

import com.sistema.pedidos.DTO.VentasDetalleDTO;
import com.sistema.pedidos.entity.VentaDetalleEntity;
import com.sistema.pedidos.repository.VentasDetalleRepository;
import com.sistema.pedidos.service.VentasDetalleServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class VentasDetalleServicesImpl implements VentasDetalleServices {
    @Autowired
    private ModelMapper modelMapper;

    private VentasDetalleRepository ventasDetalleRepository;

    @Override
    public ResponseEntity<List<VentasDetalleDTO>> save(List<VentasDetalleDTO> ventasDetalleDTOList) {
        try {
            List<VentaDetalleEntity> ventaDetalleEntities = new ArrayList<>();
            // Convierte cada DTO a entidad y agrega a la lista de entidades
            for (VentasDetalleDTO ventasDetalleDTO : ventasDetalleDTOList) {
                VentaDetalleEntity ventaDetalleEntity = mapearEntidad(ventasDetalleDTO);
                ventaDetalleEntities.add(ventaDetalleEntity);
            }
            // Guarda la lista de entidades en la base de datos
            ventaDetalleEntities = ventasDetalleRepository.saveAll(ventaDetalleEntities);
            // Convierte la lista de entidades guardadas de nuevo a DTOs
            List<VentasDetalleDTO> ventasDetalleGuardadoDTOList = new ArrayList<>();
            for (VentaDetalleEntity ventaDetalleEntity : ventaDetalleEntities) {
                ventasDetalleGuardadoDTOList.add(mapearDTO(ventaDetalleEntity));
            }
            // Retorna la respuesta con la lista de DTOs del detalle de ventas guardados
            return new ResponseEntity<>(ventasDetalleGuardadoDTOList, HttpStatus.CREATED);
        } catch (Exception e) {
            // En caso de error, maneja la excepción y devuelve una respuesta de error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    private VentasDetalleDTO mapearDTO(VentaDetalleEntity venta) {
        VentasDetalleDTO ventaDTO = modelMapper.map(venta, VentasDetalleDTO.class);
        return ventaDTO;
    }

    // Convierte de DTO a Entidad
    private VentaDetalleEntity mapearEntidad(VentasDetalleDTO ventasDTO) {
        VentaDetalleEntity venta = modelMapper.map(ventasDTO, VentaDetalleEntity.class);
        return venta;
    }


}














