package com.sistema.pedidos.service;

import com.sistema.pedidos.DTO.PromocionDTO;
import com.sistema.pedidos.commons.GenericServiceApi;
import com.sistema.pedidos.entity.Promocion;

import java.util.List;

public interface PromocionService extends GenericServiceApi<Promocion, Long> {
    List<PromocionDTO> getPromocionesDTOActivas();
}
