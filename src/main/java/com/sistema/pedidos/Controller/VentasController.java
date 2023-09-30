package com.sistema.pedidos.Controller;

import com.sistema.pedidos.DTO.VentasDTO;
import com.sistema.pedidos.service.VentasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RestController
@RequestMapping("/venta")
@CrossOrigin("*")
public class VentasController {
    @Autowired
    VentasServices ventasServices;
    @PostMapping("/guardar")
    public ResponseEntity<VentasDTO> guardar(@Valid @RequestBody VentasDTO ventasDTOList) {
        VentasDTO ventasGuardadas = ventasServices.save(ventasDTOList).getBody();
        if (ventasGuardadas != null) {
            return new ResponseEntity<>(ventasGuardadas, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
