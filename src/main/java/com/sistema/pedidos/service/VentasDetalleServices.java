package com.sistema.pedidos.service;

import com.sistema.pedidos.DTO.VentasDTO;
import com.sistema.pedidos.DTO.VentasDetalleDTO;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public interface VentasDetalleServices {
    public ResponseEntity<List<VentasDetalleDTO>> save(List<VentasDetalleDTO> ventasDTO);
}
