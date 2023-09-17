package com.sistema.pedidos.Controller;

import com.sistema.pedidos.entity.Empleado;
import com.sistema.pedidos.entity.Evento;
import com.sistema.pedidos.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/Evento")
public class EventoController {
@Autowired
    EventoService eventoService;
    @GetMapping("/list")
    public ResponseEntity<Iterable<Evento>> listarEvento() {
        return new ResponseEntity<>(eventoService.listarEvento(), HttpStatus.OK);
    }
}
