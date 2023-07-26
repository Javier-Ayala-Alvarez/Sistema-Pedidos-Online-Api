package com.sistema.pedidos.Controller;

import com.sistema.pedidos.DTO.SavePlatoDTO;
import com.sistema.pedidos.entity.Plato;
import com.sistema.pedidos.service.impl.PlatoServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/platos")
public class PlatoController {
    private final PlatoServiceImpl platoService;
    @GetMapping("/list")
    public ResponseEntity<Iterable<Plato>> listarPlatos() {
        return new ResponseEntity<>(platoService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Object> agregarPlato(@RequestBody SavePlatoDTO savePlatoDTO) {
        return this.platoService.saveValidate(savePlatoDTO);
    }
}
