package com.sistema.pedidos.service;

import com.sistema.pedidos.DTO.VentasDetalleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public interface VentasDetalleServices {
    public ResponseEntity<Object> save(ArrayList<VentasDetalleDTO> ventasDTO);
    public ResponseEntity<List<VentasDetalleDTO>> buscarId(long id);

}
