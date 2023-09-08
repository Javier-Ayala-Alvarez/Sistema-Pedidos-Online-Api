package com.sistema.pedidos.service;

import com.sistema.pedidos.DTO.PlatoDTO;
import com.sistema.pedidos.commons.GenericServiceApi;
import com.sistema.pedidos.entity.Empleado;
import com.sistema.pedidos.entity.Plato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface PlatoService extends GenericServiceApi<Plato,Long> {
    Page<PlatoDTO> listPlateActiveWithPagination(Pageable pageable);

    ResponseEntity updatePlateState(Boolean estado, Long id);

    Optional<Plato> getPlateById(Long id);

}
