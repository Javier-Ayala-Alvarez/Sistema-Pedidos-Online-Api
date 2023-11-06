package com.sistema.pedidos.service.impl;

import com.sistema.pedidos.DTO.VentasDetalleDTO;
import com.sistema.pedidos.entity.Product;
import com.sistema.pedidos.entity.VentaDetalleEntity;
import com.sistema.pedidos.entity.VentaEntity;
import com.sistema.pedidos.repository.VentasDetalleRepository;
import com.sistema.pedidos.service.VentasDetalleServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VentasDetalleServicesImpl implements VentasDetalleServices {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private VentasDetalleRepository ventasDetalleRepository;

    @Override
    public ResponseEntity<Object> save(ArrayList<VentasDetalleDTO> ventasDetalleDTOList) {
        try {
            // Mapea los DTO a entidades
            List<VentaDetalleEntity> ventaDetalleEntities = ventasDetalleDTOList.stream()
                    .map(this::mapearEntidad)
                    .collect(Collectors.toList());

            // Guarda las entidades en la base de datos
            ventaDetalleEntities = ventasDetalleRepository.saveAll(ventaDetalleEntities);

            // Mapea las entidades guardadas de nuevo a DTOs
            List<VentasDetalleDTO> ventasDetalleGuardadoDTOList = ventaDetalleEntities.stream()
                    .map(this::mapearDTO)
                    .collect(Collectors.toList());

            // Retorna la respuesta con la lista de DTOs de ventas guardados
            return new ResponseEntity<>(ventasDetalleGuardadoDTOList, HttpStatus.CREATED);
        } catch (Exception e) {
            e.getStackTrace();
            return new ResponseEntity<>(Collections.singletonMap("error", e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<VentasDetalleDTO>> buscarId(long id) {
        try {
            List<VentaDetalleEntity> ventaDetalleEntities = ventasDetalleRepository.buscar(id);
            if (!ventaDetalleEntities.isEmpty()) {
                List<VentasDetalleDTO> dtos = new ArrayList<>();

                for (VentaDetalleEntity ventaDetalleEntity : ventaDetalleEntities) {
                    if(ventaDetalleEntity.getProduct() == null){
                        Product product = new Product();

                        product.setId(ventaDetalleEntity.getPlato().getId());
                        product.setUrlImagen(ventaDetalleEntity.getPlato().getUrlImagen());
                        product.setNombre(ventaDetalleEntity.getPlato().getNombre());
                        ventaDetalleEntity.setProduct(product);

                    }
                    VentasDetalleDTO dto = mapearDTO(ventaDetalleEntity); // Asumiendo que mapearDTO devuelve un DTO
                    dtos.add(dto);
                }

                return new ResponseEntity<>(dtos, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Esto es útil para depurar errores
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





    private VentasDetalleDTO mapearDTO(VentaDetalleEntity venta) {
        VentasDetalleDTO ventaDTO = modelMapper.map(venta, VentasDetalleDTO.class);
        if (venta.getProduct() != null && venta.getProduct().getId() == 0) {
            ventaDTO.setProduct(null);
        }
        if (venta.getPlato() != null && venta.getPlato().getId() == 0) {
            ventaDTO.setPlato(null);
        }
        return ventaDTO;
    }

    // Convierte de DTO a Entidad
    private VentaDetalleEntity mapearEntidad(VentasDetalleDTO ventasDTO) {
        VentaDetalleEntity venta = modelMapper.map(ventasDTO, VentaDetalleEntity.class);
        // Manejar el caso de product con id null
        if (ventasDTO.getProduct() != null && ventasDTO.getProduct().getId() == 0) {
            venta.setProduct(null);
        }
        if (ventasDTO.getPlato() != null && ventasDTO.getPlato().getId() == 0) {
            venta.setPlato(null);
        }
        return venta;
    }



}


















