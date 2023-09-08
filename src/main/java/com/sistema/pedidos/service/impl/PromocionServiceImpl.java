package com.sistema.pedidos.service.impl;

import com.sistema.pedidos.DTO.PromocionDTO;
import com.sistema.pedidos.commons.GenericServiceImpl;
import com.sistema.pedidos.entity.Promocion;
import com.sistema.pedidos.repository.PromocionRepository;
import com.sistema.pedidos.service.PromocionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PromocionServiceImpl extends GenericServiceImpl<Promocion, Long> implements PromocionService {
    private final PromocionRepository promocionRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    @Override
    public JpaRepository getDao() {
        return promocionRepository;
    }

    @Override
    public List<PromocionDTO> getPromocionesDTOActivas() {
        List<Promocion> promociones = promocionRepository.findAllByEstado();
        List<PromocionDTO> promocionDTOList = new ArrayList<>();
        for (Promocion promocion: promociones) {
            PromocionDTO promocionDTO = modelMapper.map(promocion, PromocionDTO.class);
            promocionDTOList.add(promocionDTO);
        }
        return promocionDTOList;
    }
}
