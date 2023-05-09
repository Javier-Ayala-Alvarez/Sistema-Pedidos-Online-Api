package com.sistema.pedidos.Controller;

import com.sistema.pedidos.DTO.PuestoDTO;
import com.sistema.pedidos.DTO.PuestoRespuesta;
import com.sistema.pedidos.Utileria.ConstantUtileria;
import com.sistema.pedidos.repository.PuestoRepository;
import com.sistema.pedidos.service.PuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/Puesto")
public class PuestoController {
    @Autowired
    private PuestoService puestoService;
    @Autowired
    private PuestoRepository puestoRepository;


    @GetMapping("/todos")
    public ResponseEntity<PuestoRespuesta> todos(
            @RequestParam(value = "pageNo", defaultValue = ConstantUtileria.NUMERO_PAGINA_DEFECTO, required = false) Integer numeroDePagina,
            @RequestParam(value = "pageSize", defaultValue = ConstantUtileria.MEDIDA_PAGINA_DEFECTO, required = false) Integer medidaDePagina,
            @RequestParam(value = "sortBy", defaultValue = ConstantUtileria.ORDENAR_DEFECTO, required = false) String ordenarPor,
            @RequestParam(value = "sortDir", defaultValue = ConstantUtileria.ORDENAR_DIRECCION_DEFECTO, required = false) String sortDir

    ) {
        PuestoRespuesta puestoRespuesta = puestoService.mostrarTodos(numeroDePagina, medidaDePagina, ordenarPor, sortDir).getBody();
        return new ResponseEntity<>(puestoRespuesta, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<PuestoDTO> puesto(@RequestParam(name = "id") Integer id) {
        return ResponseEntity.ok(puestoService.mostrarId(id));

    }

    @PostMapping("/guardar")
    public ResponseEntity<PuestoDTO> guardar(@Valid @RequestBody PuestoDTO puesto) {
        return new ResponseEntity<>(puestoService.guardar(puesto), HttpStatus.CREATED);
    }

    @PutMapping("/editar")
    public ResponseEntity<PuestoDTO> editar(@Valid @RequestBody PuestoDTO puesto, @RequestParam(name = "id") Integer id) {
        return new ResponseEntity<>(puestoService.editar(puesto, id), HttpStatus.OK);

    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminar(@RequestParam(name = "id") Integer id) {
        puestoService.eliminar(id);
        return new ResponseEntity<>("Sucursal eliminada con exito", HttpStatus.OK);
    }


}
