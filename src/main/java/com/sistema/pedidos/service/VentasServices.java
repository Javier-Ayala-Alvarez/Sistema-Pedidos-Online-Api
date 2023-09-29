package com.sistema.pedidos.service;

import com.sistema.pedidos.DTO.VentasDTO;
import org.springframework.http.ResponseEntity;

public interface VentasServices {
    public ResponseEntity<VentasDTO> save(VentasDTO ventasDTO);
}
