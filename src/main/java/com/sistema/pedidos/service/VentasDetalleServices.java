package com.sistema.pedidos.service;

import com.sistema.pedidos.DTO.VentasDetalleDTO;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public interface VentasDetalleServices {
    public ResponseEntity<Object> save(ArrayList<VentasDetalleDTO> ventasDTO);
}
