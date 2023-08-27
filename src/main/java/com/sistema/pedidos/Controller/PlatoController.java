package com.sistema.pedidos.Controller;

import com.sistema.pedidos.DTO.PlatoDTO;
import com.sistema.pedidos.DTO.SavePlatoDTO;
import com.sistema.pedidos.Utileria.ConstantUtileria;
import com.sistema.pedidos.entity.Plato;
import com.sistema.pedidos.service.impl.PlatoServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@Validated
@RequestMapping("/api/platos")
public class PlatoController {
    private final PlatoServiceImpl platoService;

    @GetMapping("/list")
    public ResponseEntity<Iterable<Plato>> listarPlatos() {
        return new ResponseEntity<>(platoService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Object> agregarPlato(@Valid @RequestBody SavePlatoDTO savePlatoDTO) {
        return this.platoService.saveValidate(savePlatoDTO);
    }

    @GetMapping("/list/pageables")
    public ResponseEntity<Page<PlatoDTO>> listarPlatosActivos(
            @RequestParam(defaultValue = ConstantUtileria.NUMERO_PAGINA_DEFECTO) int page,
            @RequestParam(defaultValue = ConstantUtileria.MEDIDA_PAGINA_DEFECTO) int size,
            @RequestParam(defaultValue = ConstantUtileria.ORDENAR_DEFECTO) String order,
            @RequestParam(defaultValue = ConstantUtileria.ORDENAR_DIRECCION_DEFECTO) boolean asc
    ) {
        Page<PlatoDTO> categoryPage=platoService.listPlateActiveWithPagination(
                PageRequest.of(page,size, Sort.by(order)));

        return new ResponseEntity<Page<PlatoDTO>>(categoryPage, HttpStatus.OK);
    }

    @PutMapping("/baja/{id}")
    ResponseEntity darDeBajaPlato(@PathVariable Long id){
        return platoService.updatePlateState(Boolean.FALSE,id);
    }

    @PutMapping("/actualizar")
    ResponseEntity actualizarPlato(@RequestBody SavePlatoDTO plato){
        return platoService.updatePlato(plato);
    }

}
