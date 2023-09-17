package com.sistema.pedidos.service.impl;

import com.sistema.pedidos.entity.Evento;
import com.sistema.pedidos.entity.Product;
import com.sistema.pedidos.repository.EventoRepository;
import com.sistema.pedidos.service.EventoService;
import mapper.PlatoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoServicesImpl implements EventoService {

private EventoRepository eventoRepository;
    @Override
    public List<Evento> listarEvento() {
        return new ResponseEntity<>(eventoRepository.findAll(), HttpStatus.CREATED).getBody();

    }
}
