package com.sistema.pedidos.Controller;

import com.sistema.pedidos.service.PromocionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/promocion")
public class PromocionController {
    private final PromocionService promocionService;

    @GetMapping("/list/DTO/activo")
    ResponseEntity<?> listarPromocionDTOActivo(){
        return  ResponseEntity.ok(promocionService.getPromocionesDTOActivas());

    }
}
